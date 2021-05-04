package uz.pdp.lesson7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson7.controller.LavozimController;
import uz.pdp.lesson7.entity.Lavozim;
import uz.pdp.lesson7.payload.ApiResponse;
import uz.pdp.lesson7.payload.LavozimDto;
import uz.pdp.lesson7.repository.LavozimRepository;

@Service
public class LavozimService {

    @Autowired
    LavozimRepository lavozimRepository;

    public ApiResponse addLavozim(LavozimDto lavozimDto) {
        if (lavozimRepository.existsByName(lavozimDto.getName()))
            return new ApiResponse("Bunday lavozim bor", false);

        Lavozim lavozim = new Lavozim(
                lavozimDto.getName(),
                lavozimDto.getHuquqList(),
                lavozimDto.getDescription()
        );
        lavozimRepository.save(lavozim);

        return new ApiResponse("Saqlandi", true);
    }

    public ApiResponse editLavozim(Long id, LavozimDto lavozimDto) {

        return null;
    }
}
