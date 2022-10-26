package com.ronald.dto;

import com.ronald.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Integer id;
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50)
    private String nameCategory;
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 150)
    //@Pattern(regexp = "[A-Za-z]*")
    //@Email
    private String descriptionCategory;
    @NotNull
    private boolean enabledCategory;

    public CategoryDTO(Category category) {
        this.id = category.getIdCategory();
        this.nameCategory = category.getName();
        this.descriptionCategory = category.getDescription();
        this.enabledCategory = category.isEnabled();
    }
}
