/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import domainModels.NhaCungCap;
import java.util.List;
import repositories.NhaCungCapRepository;
import service.impl.IManageNhaCungCapService;

/**
 *
 * @author Uy Tin
 */
public class NhaCungCapService implements IManageNhaCungCapService {

    private NhaCungCapRepository NhaCungCapRepository = new NhaCungCapRepository();

    @Override
    public List<NhaCungCap> getAll() {
        return NhaCungCapRepository.getAll();
    }

    @Override
    public Boolean them(NhaCungCap ncc) {
        return NhaCungCapRepository.add(ncc);
    }

    @Override
    public Boolean sua(NhaCungCap ncc) {
        return NhaCungCapRepository.update(ncc);
    }

    @Override
    public Boolean xoa(NhaCungCap ncc) {
        return NhaCungCapRepository.delete(ncc);
    }

    @Override
    public NhaCungCap getOne(String ten) {
        return NhaCungCapRepository.getOne(ten);
    }

}
