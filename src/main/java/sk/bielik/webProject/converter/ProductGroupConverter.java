package sk.bielik.webProject.converter;

import sk.bielik.webProject.entity.enums.ProductGroup;

import javax.persistence.AttributeConverter;

public class ProductGroupConverter implements AttributeConverter<ProductGroup,Character> {
    @Override
    public Character convertToDatabaseColumn(ProductGroup productGroup) {
        if (productGroup==null){
            return null;
        }
       return productGroup.getKode();
    }

    @Override
    public ProductGroup convertToEntityAttribute(Character character) {
        if (character==null){
            return null;
        }
        return ProductGroup.getEnumFromCharacter(character);
    }
}
