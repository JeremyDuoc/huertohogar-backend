package com.tienda.backend;

import com.tienda.backend.model.Producto;
import com.tienda.backend.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HuertohogarBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuertohogarBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ProductoRepository repository) {
        return (args) -> {
            if(repository.count() == 0) {
                
            
                Producto p1 = new Producto();
                p1.setName("Manzanas Fuji");       
                p1.setPrice(1200);                
                p1.setImage("https://freshmate.cl/cdn/shop/files/manzanas_fuji_en_bowl_de_madera_21_11zon.webp?v=1724716431"); 
                p1.setDescription("Crujientes y dulces, cultivadas en el Valle del Maule."); 
                p1.setCategory("Frutas");         
                repository.save(p1);

                Producto p2 = new Producto();
                p2.setName("Naranjas Valencia");
                p2.setPrice(1000);
                p2.setImage("https://www.frutamare.com/wp-content/uploads/2020/05/naranja-valencia-late.jpg");
                p2.setDescription("Jugosas y ricas en vitamina C. Ideales para zumos.");
                p2.setCategory("Frutas");
                repository.save(p2);

                Producto p3 = new Producto();
                p3.setName("Plátanos Cavendish");
                p3.setPrice(800);
                p3.setImage("https://st3.depositphotos.com/1020804/19000/i/450/depositphotos_190006620-stock-photo-banana-bunch-on-the-wooden.jpg");
                p3.setDescription("Ricos en potasio y vitaminas. El snack perfecto.");
                p3.setCategory("Frutas");
                repository.save(p3);

               
                Producto p4 = new Producto();
                p4.setName("Zanahorias Orgánicas");
                p4.setPrice(900);
                p4.setImage("https://organicocr.store/wp-content/uploads/2018/04/zanahoria.jpg");
                p4.setDescription("Cultivadas sin pesticidas en O'Higgins. Ricas en vitamina A.");
                p4.setCategory("Verduras");
                repository.save(p4);

        
                Producto p5 = new Producto();
                p5.setName("Espinacas Frescas");
                p5.setPrice(700);
                p5.setImage("https://i.blogs.es/10d3c5/espinacas-rec/1366_2000.jpg");
                p5.setDescription("Cultivadas orgánicamente, ideales para ensaladas.");
                p5.setCategory("Verduras");
                repository.save(p5);


                Producto p6 = new Producto();
                p6.setName("Pimientos Tricolores");
                p6.setPrice(1500);
                p6.setImage("https://www.lavanguardia.com/files/og_thumbnail/uploads/2019/02/14/5e99838b8c8e4.jpeg");
                p6.setDescription("Ricos en antioxidantes y vitaminas. Ideales para salteados.");
                p6.setCategory("Verduras");
                repository.save(p6);

                System.out.println(">>> ¡Los productos han sido cargados! <<<");
            }
        };
    }
}