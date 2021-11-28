package jpabook.jpashop.repository.order.query;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

	private final EntityManager em;
	
	public List<OrderQueryDto> findOrderQueryDtos(){
		List<OrderQueryDto> result=findOrders();
		
		result.forEach(o->{
			List<OrderItemQueryDto> orderItems=findOrderItems(o.getOrderId());
		});
	}
	
	private List<OrderQueryDto> findOrders(){
		return em.createQuery(
				"select new jpabook.jpashop.repository.order.query.OrderQueryDto(o.id,m.name,o.orderDate,o.status,d.address)"+
				" from Order o"+
				" join o.member m"+
				" join o.delivery d",OrderQueryDto.class).getResultList();
	}
	
	private List<OrderItemQueryDto> findOrderItems(Long orderId){
		return null;
	}
}
