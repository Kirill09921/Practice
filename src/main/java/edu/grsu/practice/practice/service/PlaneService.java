package edu.grsu.practice.practice.service;

import edu.grsu.practice.practice.dto.BookingDto;
import edu.grsu.practice.practice.dto.PlaneDto;
import edu.grsu.practice.practice.model.Plane;

import java.util.List;
import java.util.UUID;

public interface PlaneService {
    public PlaneDto addPlane(PlaneDto planeDto);
    public List<PlaneDto> getAllPlanes();
    public PlaneDto getPlane(UUID planeId);
    public Plane getPlaneEntity(UUID planeId);
    public boolean deletePlane(UUID planeId);
    public PlaneDto updatePlane(PlaneDto planeDto);
}
