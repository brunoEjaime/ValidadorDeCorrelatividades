//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Alumno {
    private List<Materia> materiasAprobadas = new ArrayList();
    private String nombre;



    public void agregarMateriasAprobadas(Materia... aprobadas) {
        Collections.addAll(this.materiasAprobadas, aprobadas);
    }


    public List<Materia> getMateriasAprobadas() {
        return this.materiasAprobadas;
    }


    public void agregarAprobadas(Materia... aprobadas) {
        Collections.addAll(this.materiasAprobadas, aprobadas);
    }
    public void setMateriasAprobadas(List<Materia> materiasAprobadas) {
        this.materiasAprobadas = materiasAprobadas;
    }
    public Boolean aprobada(Materia materia) {
        return this.materiasAprobadas.contains(materia);
    }
}
