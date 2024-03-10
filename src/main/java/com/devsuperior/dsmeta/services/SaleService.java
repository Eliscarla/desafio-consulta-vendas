package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.dto.*;
import com.devsuperior.dsmeta.projections.ReportProjection;
import com.devsuperior.dsmeta.projections.SumaryProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	@Transactional(readOnly = true)
	public Page<ReportProjectionDto> getReport (String DATA_INICIAL, String DATA_FINAL, String NAME, Pageable pageable ){

		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dataEnd = (DATA_FINAL != null)   ? LocalDate.parse(DATA_FINAL,   format) :  today;
		LocalDate dataIni = (DATA_INICIAL != null) ? LocalDate.parse(DATA_INICIAL, format) :  dataEnd.minusYears(1L);

		Page<ReportProjection> projections = repository.getReport(dataIni, dataEnd, NAME, pageable);
		return projections.map(p ->  new ReportProjectionDto(p));
	};

	@Transactional(readOnly = true)
	public List<SumaryProjectionDto> getSumary (String DATA_INICIAL, String DATA_FINAL){
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dataEnd = (DATA_FINAL != null)   ? LocalDate.parse(DATA_FINAL,   format) :  today;
		LocalDate dataIni = (DATA_INICIAL != null) ? LocalDate.parse(DATA_INICIAL, format) :  dataEnd.minusYears(1L);
		List<SumaryProjection> projections = repository.getSumary(dataIni, dataEnd);
		return projections.stream().map(p ->  new SumaryProjectionDto(p)).collect(Collectors.toList());
	};
}
