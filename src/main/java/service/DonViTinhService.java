/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import domainModels.DonViTinh;
import java.util.List;
import repositories.DonViTinhRepository;
import service.impl.IManageDonViTinhService;

/**
 *
 * @author Uy Tin
 */
public class DonViTinhService implements IManageDonViTinhService {

    private DonViTinhRepository donViTinhRepository = new DonViTinhRepository();

    @Override
    public List<DonViTinh> getAll() {
        return donViTinhRepository.getAll();
    }

    @Override
    public boolean add(DonViTinh dvt) {
        return donViTinhRepository.add(dvt);
    }

    @Override
    public boolean update(DonViTinh dvt) {
        return donViTinhRepository.update(dvt);
    }

    @Override
    public boolean delete(DonViTinh dvt) {
        return donViTinhRepository.delete(dvt);
    }

    @Override
    public DonViTinh getOne(String ten) {
        return donViTinhRepository.getOne(ten);
    }

}
