/*
 
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template*/
package persistencia;

/**
 *
 
@author arthurce*/
import java.util.List;

public interface BasicoDAO {
    void salvar(Object bean);
    void atualizar(Object bean);
    void deletar(int id);
    Object getById(int id);
    List<Object> getAll();
}
