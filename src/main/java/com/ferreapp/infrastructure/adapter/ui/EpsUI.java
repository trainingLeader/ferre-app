package com.ferreapp.infrastructure.adapter.ui;

import java.util.Scanner;

import com.ferreapp.application.usecase.EpsUseCase;
import com.ferreapp.domain.entities.Eps;
import com.ferreapp.domain.repositories.EpsRepository;
import com.ferreapp.infrastructure.database.ConnectMysqlFactory;
import com.ferreapp.infrastructure.persistence.EpsRepositoryImpl;

public class EpsUI {
    EpsRepository repository = new EpsRepositoryImpl(ConnectMysqlFactory.crearConexion());
    EpsUseCase  useCase = new EpsUseCase(repository);

    public void CreateEps() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Ingrese el nombre de la EPS");
            String name = sc.nextLine();
            useCase.save(name);
        }
    }
    public void UpdateEps() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Ingrese el id de la EPS");
            int id = sc.nextInt();
            System.out.println("Ingrese el nombre de la EPS");
            String name = sc.nextLine();
            useCase.update(id, name);
        }
    }
    public void DeleteEps() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Ingrese el id de la EPS");
            int id = sc.nextInt();
            useCase.delete(id);
        }
    }
    public void FindEps() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Ingrese el id de la EPS");
            int id = sc.nextInt();
            Eps eps = useCase.findById(id);
            System.out.println("Id: " + eps.getId() + " Nombre: " + eps.getName());
        }
    }
}
