package guavapay.guavapay.mapper;

import guavapay.guavapay.dto.UsersDto;
import guavapay.guavapay.model.Users;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UsersMapper {

    public static final UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);


    @Mappings({
            @Mapping(target = "username", source = "users.username"),
            @Mapping(target = "password", source = "users.password")
    })
    UsersDto toDTO (Users users);


    @Mappings({
            @Mapping(target = "username", source = "usersDto.username"),
            @Mapping(target = "password", source = "usersDto.password")
    })
    public abstract Users users (UsersDto usersDto);





}
