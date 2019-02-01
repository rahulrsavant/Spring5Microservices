package com.order.util.converter;

import com.order.dto.OrderLineDto;
import com.order.model.Order;
import com.order.model.OrderLine;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Mapper
@DecoratedWith(OrderLineConverterDecorator.class)
public interface OrderLineConverter {

    /**
     * Create a new {@link OrderLine} which properties match with the given {@link OrderLineDto}
     *
     * @param orderLineDto
     *    {@link OrderLineDto} with the "source information"
     * @param orderId
     *    {@link Order#id} of the given dto
     *
     * @return {@link OrderLine}
     */
    @Mappings(@Mapping(source = "orderLineDto.pizza.id", target = "pizzaId"))
    OrderLine fromDtoToModel(OrderLineDto orderLineDto, Integer orderId);

    /**
     * Create a new {@link OrderLine} which properties match with the given {@link OrderLineDto}
     *
     * @param orderLineDto
     *    {@link OrderLineDto} with the "source information"
     *
     * @return {@link Optional} of {@link OrderLine}
     */
    default Optional<OrderLine> fromDtoToOptionalModel(OrderLineDto orderLineDto, Integer orderId) {
        return Optional.ofNullable(orderLineDto)
                       .map(dto -> this.fromDtoToModel(dto, orderId));
    }

    /**
     *    Return a new {@link Collection} of {@link OrderLine} with the information contains in the given
     * {@link Collection} of {@link OrderLineDto}
     *
     * @param orderLineDtos
     *    {@link Collection} of {@link OrderLineDto} with the "source information"
     * @param orderId
     *    {@link Order#id} of the given dtos
     *
     * @return {@link Collection} of {@link OrderLine}
     */
    default Collection<OrderLine> fromDtosToModels(Collection<OrderLineDto> orderLineDtos, Integer orderId) {
        return Optional.ofNullable(orderLineDtos)
                       .map(dtos -> {
                           List<OrderLine> orderLines = new ArrayList<>();
                           dtos.forEach(dto -> orderLines.add(this.fromDtoToModel(dto, orderId)));
                           return orderLines;
                       })
                       .orElse(new ArrayList<>());
    }

}


/**
 * Overwrite default converter methods included in {@link OrderLineConverter}
 */
abstract class OrderLineConverterDecorator implements OrderLineConverter {

    @Autowired
    private OrderLineConverter orderLineConverter;

    /**
     *    Create a new {@link OrderLine} which properties match with the given {@link OrderLineDto}. The difference
     * with the "default behaviour" is that only if the given {@link OrderLineDto} is not null, we will create a new one.
     *
     * @param orderLineDto
     *    {@link OrderLineDto} with the "source information"
     * @param orderId
     *    {@link Order#id} of the given dto
     *
     * @return {@link OrderLine}
     */
    @Override
    public OrderLine fromDtoToModel(OrderLineDto orderLineDto, Integer orderId) {
        return Optional.ofNullable(orderLineDto)
                       .map(dto -> orderLineConverter.fromDtoToModel(dto, orderId))
                       .orElse(null);
    }

}
