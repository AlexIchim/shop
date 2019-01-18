package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Revenue;
import ro.msg.learning.shop.repository.RevenueRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class RevenueService {

	private final RevenueRepository revenueRepository;

	public List<Revenue> saveAll(List<Revenue> revenueList) {
		return revenueRepository.saveAll(revenueList);
	}
}
