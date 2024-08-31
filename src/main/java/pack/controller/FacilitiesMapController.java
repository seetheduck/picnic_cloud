package pack.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pack.dto.FacilitiesMapDto;
import pack.service.FacilitiesMapService;

@Controller
@Tag(name = "지도", description = "지도 관련 API입니다.")
public class FacilitiesMapController {
	@Autowired
    private FacilitiesMapService facilitiesMapService;

    public FacilitiesMapController(FacilitiesMapService facilitiesMapService) {
        this.facilitiesMapService = facilitiesMapService;
    }

    // 뷰를 반환하는 메서드
    @GetMapping("/map")
    public String toMap() {
        return "redirect:/mapIndex.html";
    }

    // API 데이터를 반환하는 메서드
    @Operation(summary = "지도 API 데이터를 반환", description = "지도 API 데이터를 반환.")
    @GetMapping("/api/facilities")
    @ResponseBody
    public List<FacilitiesMapDto> getAllFacilities() {
        return facilitiesMapService.getAllFacilities();
    }
}
