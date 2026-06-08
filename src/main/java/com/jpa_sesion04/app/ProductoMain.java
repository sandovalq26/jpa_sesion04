package com.jpa_sesion04.app;

import com.jpa_sesion04.model.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ProductoMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sesion04_pu");
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Producto> query = em.createQuery(
                    "SELECT p FROM Producto p JOIN FETCH p.categoria JOIN FETCH p.proveedor ORDER BY p.idProd",
                    Producto.class
            );

            List<Producto> usuarios = query.getResultList();

            System.out.println("===========================================");
            System.out.println("         LISTADO DE PRODUCTOS               ");
            System.out.println("===========================================");

            for (Producto u : usuarios) {
                System.out.println("Descripcion : " + u.getDesProd());
                System.out.println("Stock : " + u.getStkProd());
                System.out.println("Precio : " + u.getPreProd());
                System.out.println("Categoria : " + u.getCategoria().getDescripcion());
                System.out.println("Estado : " + u.getEstProd());
                System.out.println("Proveedor : " + u.getProveedor().getNombreRs());
                System.out.println("-------------------------------------------");
            }

            System.out.println("Total de usuarios: " + usuarios.size());

        } finally {
            em.close();
            emf.close();
        }
    }
}
