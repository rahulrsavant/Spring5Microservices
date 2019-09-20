package com.pizza.dto;

import com.pizza.enums.PizzaEnum;
import com.spring5microservices.common.validator.annotation.EnumHasInternalStringValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PizzaDto {

    private Integer id;

    @NotNull
    @EnumHasInternalStringValue(enumClass=PizzaEnum.class)
    private String name;

    @NotNull
    @Positive
    private Double cost;

    @Valid
    private Set<IngredientDto> ingredients;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (null == o || getClass() != o.getClass()) return false;
        PizzaDto pizza = (PizzaDto) o;
        return name.equals(pizza.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
