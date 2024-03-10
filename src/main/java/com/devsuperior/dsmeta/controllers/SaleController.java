package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.ReportProjectionDto;
import com.devsuperior.dsmeta.dto.SumaryProjectionDto;
import com.devsuperior.dsmeta.projections.SumaryProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<ReportProjectionDto>> getReport(@RequestParam(required = false, value = "name") String name,
														       @RequestParam(required = false, value = "minDate") String dateIni,
															   @RequestParam(required = false, value = "maxDate") String dateEnd,
															   Pageable pageable) {

		Page<ReportProjectionDto> report = service.getReport(dateIni, dateEnd, name, pageable);
		return ResponseEntity.ok(report);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SumaryProjectionDto>> getSumary(@RequestParam(required = false, value = "minDate") String dateIni,
															   @RequestParam(required = false, value = "maxDate") String dateEnd ) {
		List<SumaryProjectionDto> list = service.getSumary(dateIni, dateEnd);
		return  ResponseEntity.ok(list);

	}
}
