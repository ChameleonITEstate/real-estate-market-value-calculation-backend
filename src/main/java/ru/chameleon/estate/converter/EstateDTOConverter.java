package ru.chameleon.estate.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.chameleon.estate.dto.UserDTO;
import ru.chameleon.estate.entity.User;

@Component
public class EstateDTOConverter {
    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User convertToEntity(UserDTO userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
