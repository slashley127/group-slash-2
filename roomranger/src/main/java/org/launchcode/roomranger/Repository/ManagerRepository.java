package org.launchcode.roomranger.Repository;
import org.launchcode.roomranger.models.Manager;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ManagerRepository extends PagingAndSortingRepository<Manager, Integer> {
}