package com.flipkart.business;

public interface AdminServiceInterface {
    public Object approveGymCenterById(String gymCenterId);
    public Object approveGymOwnerById(String gymOwnerId);
    public Object approveAllGymCenters();
    public Object approveAllGymOwners();
    public Object listPendingGymCenters();
    public Object listPendingGymOwners();
    public Object listGymCenters();
    public Object listGymOwners();
    public Object listUsers();
}
