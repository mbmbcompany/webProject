package sk.bielik.webProject.service;

import org.mapstruct.Mapper;
import sk.bielik.webProject.entity.TrolleyItem;
import sk.bielik.webProject.entityDto.TrolleyItemDto;

@Mapper
public interface TrolleyItemMapper {

    TrolleyItem mapTrolleyItemDtoToTrolleyItem(TrolleyItemDto trolleyItemDto);

    TrolleyItemDto mapTrolleyItemToTrolleyItemDto(TrolleyItem trolleyItem);
}
