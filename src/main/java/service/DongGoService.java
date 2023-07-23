/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import domainModels.DongGo;
import java.util.List;
import repositories.DongGoRepository;
import service.impl.IManageDongGoService;

/**
 *
 * @author Uy Tin
 */
public class DongGoService implements IManageDongGoService {

    private DongGoRepository dongGoRepository = new DongGoRepository();

    @Override
    public List<DongGo> getAll() {
        return dongGoRepository.getAll();
    }

    @Override
    public Boolean them(DongGo dongGo) {
        return dongGoRepository.add(dongGo);
    }

    @Override
    public Boolean sua(DongGo dongGo) {
        return dongGoRepository.update(dongGo);
    }

    @Override
    public Boolean xoa(DongGo dongGo) {
        return dongGoRepository.delete(dongGo);
    }

    @Override
    public DongGo getOne(String ten) {
        return dongGoRepository.getOne(ten);
    }

}
