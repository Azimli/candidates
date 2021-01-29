package guavapay.guavapay.mapper;

import guavapay.guavapay.dto.CardTypeDto;
import guavapay.guavapay.model.CardType;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CardTypeMapper {

    public static final CardTypeMapper INSTANCE = Mappers.getMapper(CardTypeMapper.class);

    @Mappings({
            @Mapping(target = "card_type",source = "cardType.card_type"),
            @Mapping(target = "holder_name",source = "cardType.holder_name"),
            @Mapping(target = "period",source = "cardType.period"),
            @Mapping(target = "urgent",source = "cardType.urgent"),
            @Mapping(target = "codeword",source = "cardType.codeword")
    })
    CardTypeDto toDTO(CardType cardType);


    @Mappings({
            @Mapping(target = "card_type",source = "cardTypeDto.card_type"),
            @Mapping(target = "holder_name",source = "cardTypeDto.holder_name"),
            @Mapping(target = "period",source = "cardTypeDto.period"),
            @Mapping(target = "urgent",source = "cardTypeDto.urgent"),
            @Mapping(target = "codeword",source = "cardTypeDto.codeword")
    })
    CardType cardType(CardTypeDto cardTypeDto);


}
