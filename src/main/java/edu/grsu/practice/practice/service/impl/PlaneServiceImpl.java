package edu.grsu.practice.practice.service.impl;

import edu.grsu.practice.practice.dto.PlaneDto;
import edu.grsu.practice.practice.mapper.PlaneMapper;
import edu.grsu.practice.practice.model.Plane;
import edu.grsu.practice.practice.repository.PlaneRepository;
import edu.grsu.practice.practice.service.PlaneService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class PlaneServiceImpl implements PlaneService {

    public PlaneMapper planeMapper;
    public PlaneRepository planeRepository;

    @Override
    public PlaneDto addPlane(PlaneDto planeDto) {
        log.info("adding plane: {}", planeDto);
        Plane plane = planeMapper.toEntity(planeDto);
        planeRepository.save(plane);
        return planeMapper.toDto(plane);
    }

    @Override
    public List<PlaneDto> getAllPlanes() {
        log.info("getting all planes");
        List<Plane> planes = planeRepository.findAll();
        return planeMapper.toDto(planes);
    }

    @Override
    public PlaneDto getPlane(UUID planeId) {
        log.info("getting plane: {}", planeId);
        Optional<Plane> planeOptional = planeRepository.findById(planeId);
        Plane plane = planeOptional.orElseThrow();
        return planeMapper.toDto(plane);
    }

    @Override
    public boolean deletePlane(UUID planeId) {
        log.info("deleting plane: {}", planeId);
        Optional<Plane> planeOptional = planeRepository.findById(planeId);
        Plane plane = planeOptional.orElseThrow();
        planeRepository.delete(plane);
        return true;
    }

    @Override
    public PlaneDto updatePlane(PlaneDto planeDto) {
        UUID planeId =  planeDto.getId();
        log.info("updating plane: {}", planeId);
        Optional<Plane> planeOptional = planeRepository.findById(planeId);
        Plane existingPlane = planeOptional.orElseThrow();
        existingPlane = planeMapper.partialUpdate(planeDto, existingPlane);
        planeRepository.save(existingPlane);
        return planeMapper.toDto(existingPlane);
    }
}
