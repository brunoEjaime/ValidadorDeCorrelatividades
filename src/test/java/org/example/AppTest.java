package org.example;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {

    public AppTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    public void testPuedeCursarMateriaSinCorrelativas() {
        Materia algoritmos = new Materia();
        algoritmos.setNombre("Algoritmos");

        Alumno bruno = new Alumno();
        Assert.assertTrue("Bruno cumple los requisitos para cursar la materia sin correlativas", algoritmos.puedeCursar(bruno));
    }

    public void testNoPuedeCursarPorFaltaDeCorrelativas() {
        Materia algoritmos = new Materia();
        Materia pdep = new Materia();
        pdep.agregarCorrelativas(new Materia[]{algoritmos});

        Alumno bruno = new Alumno();
        Assert.assertFalse("Bruno no tiene las materias previas necesarias para inscribirse", pdep.puedeCursar(bruno));
    }

    public void testPuedeCursarConUnaCorrelativaAprobada() {
        Materia algoritmos = new Materia();
        Materia pdep = new Materia();
        pdep.agregarCorrelativas(new Materia[]{algoritmos});

        Alumno bruno = new Alumno();
        bruno.agregarMateriasAprobadas(new Materia[]{algoritmos});
        Assert.assertTrue("Bruno tiene la correlativa aprobada y puede cursar", pdep.puedeCursar(bruno));
    }

    public void testNoPuedeCursarPorCorrelativaIncompleta() {
        Materia algoritmos = new Materia();
        Materia arquitectura = new Materia();
        Materia pdep = new Materia();
        pdep.agregarCorrelativas(new Materia[]{algoritmos, arquitectura});

        Alumno bruno = new Alumno();
        bruno.agregarMateriasAprobadas(new Materia[]{algoritmos});
        Assert.assertFalse("Bruno aún debe aprobar otra correlativa para poder cursar", pdep.puedeCursar(bruno));
    }

    public void testPuedeCursarConTodasLasCorrelativasAprobadas() {
        Materia algoritmos = new Materia();
        Materia arquitectura = new Materia();
        Materia pdep = new Materia();
        pdep.agregarCorrelativas(new Materia[]{algoritmos, arquitectura});

        Alumno bruno = new Alumno();
        bruno.agregarMateriasAprobadas(new Materia[]{algoritmos, arquitectura});
        Assert.assertTrue("Bruno puede anotarse porque cumplió con todos los requisitos", pdep.puedeCursar(bruno));
    }

    public void testInscripcionNoAprobadaPorFaltaDeCorrelativas() {
        Materia algoritmos = new Materia();
        Materia arquitectura = new Materia();
        Materia pdep = new Materia();
        Materia quimica = new Materia();

        pdep.agregarCorrelativas(new Materia[]{algoritmos, arquitectura});

        Alumno bruno = new Alumno();
        bruno.agregarMateriasAprobadas(new Materia[]{algoritmos});

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.agregarMaterias(new Materia[]{pdep, quimica});

        Assert.assertFalse("La inscripción no es válida porque no cumple con todas las correlativas", inscripcion.aprobada(bruno));
    }

    public void testInscripcionAprobadaConTodosLosRequisitos() {
        Materia algoritmos = new Materia();
        Materia arquitectura = new Materia();
        Materia pdep = new Materia();
        Materia quimica = new Materia();

        pdep.agregarCorrelativas(new Materia[]{algoritmos, arquitectura});

        Alumno bruno = new Alumno();
        bruno.agregarMateriasAprobadas(new Materia[]{algoritmos, arquitectura});

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.agregarMaterias(new Materia[]{pdep, quimica});

        Assert.assertTrue("Bruno puede cursar todas las materias y su inscripción es válida", inscripcion.aprobada(bruno));
    }

    public void testPuedeCursarMateriaConCorrelativaCompuesta() {
        Materia algoritmos = new Materia();
        Materia arquitectura = new Materia();
        Materia pdep = new Materia();
        Materia disenio = new Materia();
        Materia quimica = new Materia();

        pdep.agregarCorrelativas(new Materia[]{algoritmos, arquitectura});
        disenio.agregarCorrelativas(new Materia[]{pdep});

        Alumno bruno = new Alumno();
        bruno.agregarMateriasAprobadas(new Materia[]{pdep});

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.agregarMaterias(new Materia[]{disenio, quimica});

        Assert.assertTrue("Bruno está habilitado para cursar materias con correlativas múltiples", inscripcion.aprobada(bruno));
    }
}
