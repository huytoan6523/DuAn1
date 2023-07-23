/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import domainModels.NguonGoc;
import java.util.List;
import repositories.NguonGocRepository;
import service.impl.IManageNguonGocService;

/**
 *
 * @author Uy Tin
 */
public class NguonGocService implements IManageNguonGocService {

    private NguonGocRepository NguonGocRepository = new NguonGocRepository();

    @Override
    public List<NguonGoc> getAll() {
        return NguonGocRepository.getAll();
    }

    @Override
    public boolean add(NguonGoc ng) {
        return NguonGocRepository.add(ng);
    }

    @Override
    public boolean update(NguonGoc ng) {
        return NguonGocRepository.update(ng);
    }

    @Override
    public boolean delete(NguonGoc ng) {
        return NguonGocRepository.delete(ng);
    }

    @Override
    public NguonGoc getOne(String ten) {
        return NguonGocRepository.getOne(ten);
    }

}
