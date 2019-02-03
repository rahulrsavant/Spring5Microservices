package com.pizza.util.converter;

import com.pizza.dto.PizzaDto;
import com.pizza.model.Pizza;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Mapper(uses={IngredientConverter.class})
public interface PizzaConverter {

    /**
     * Create a new {@link Pizza} which properties match with the given {@link PizzaDto}
     *
     * @param pizzaDto
     *    {@link PizzaDto} with the "source information"
     *
     * @return {@link Pizza}
     */
    Pizza fromDtoToEntity(PizzaDto pizzaDto);

    /**
     * Create a new {@link Pizza} which properties match with the given {@link PizzaDto}
     *
     * @param pizzaDto
     *    {@link PizzaDto} with the "source information"
     *
     * @return {@link Optional} of {@link Pizza}
     */
    default Optional<Pizza> fromDtoToOptionalEntity(PizzaDto pizzaDto) {
        return Optional.ofNullable(this.fromDtoToEntity(pizzaDto));
    }

    /**
     *    Return a new {@link Collection} of {@link Pizza} with the information contains in the given
     * {@link Collection} of {@link PizzaDto}
     *
     * @param pizzaDtos
     *    {@link Collection} of {@link PizzaDto} with the "source information"
     *
     * @return {@link List} of {@link Pizza}
     */
    @IterableMapping(nullValueMappingStrategy=NullValueMappingStrategy.RETURN_DEFAULT)
    List<Pizza> fromDtosToEntities(Collection<PizzaDto> pizzaDtos);

    /**
     * Create a new {@link PizzaDto} which properties match with the given {@link Pizza}
     *
     * @param pizza
     *    {@link Pizza} with the "source information"
     *
     * @return {@link PizzaDto}
     */
    PizzaDto fromEntityToDto(Pizza pizza);

    /**
     * Create a new {@link PizzaDto} which properties match with the given {@link Pizza}
     *
     * @param pizza
     *    {@link Pizza} with the "source information"
     *
     * @return {@link Optional} of {@link PizzaDto}
     */
    default Optional<PizzaDto> fromEntityToOptionalDto(Pizza pizza) {
        return Optional.ofNullable(this.fromEntityToDto(pizza));
    }

    /**
     *    Return a new {@link Collection} of {@link PizzaDto} with the information contains in the given
     * {@link Collection} of {@link Pizza}
     *
     * @param pizzas
     *    {@link Collection} of {@link Pizza} with the "source information"
     *
     * @return {@link List} of {@link PizzaDto}
     */
    @IterableMapping(nullValueMappingStrategy=NullValueMappingStrategy.RETURN_DEFAULT)
    List<PizzaDto> fromEntitiesToDtos(Collection<Pizza> pizzas);

}
