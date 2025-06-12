package edu.grsu.practice.practice.service;

import edu.grsu.practice.practice.dto.PlaneDto;

import java.util.List;
import java.util.UUID;

public interface PlaneService {
    public void addPlane(PlaneDto planeDto);
    public List<PlaneDto> getAllPlanes();
    public PlaneDto getPlane(UUID planeId);
    public void deletePlane(UUID planeId);
    public void updatePlane(UUID planeId, PlaneDto planeDto);
}
