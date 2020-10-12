package sk.bielik.webProject.service;

import org.mapstruct.Mapper;
import sk.bielik.webProject.entity.Trolley;
import sk.bielik.webProject.entityDto.TrolleyDto;

@Mapper
public interface TrolleyMapper {

    Trolley mapTrolleyDtoToTrolley(TrolleyDto trolleyDto);

    TrolleyDto mapTrolleyToTrolleyDto(Trolley trolley);
}
