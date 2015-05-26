package testare;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import obiecte.Avion;
import obiecte.AvionBuilder;
import obiecte.AvionulNuADecolatException;
import obiecte.NrLocuriNegativExcepion;
import obiecte.NuExistaPilotSauCopilotException;
import obiecte.NumarOreNegativException;
import obiecte.Pasager;
import obiecte.Pilot;
import obiecte.VolumNegativException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import centralizare.Aeroport;

public class TestareMockito {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Aeroport.getInstance().getListaAvioane().clear();
		Avion.idCurrent = 1;
		Pilot.idCurrent  = 1;
		Pasager.idCurrent = 1;
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void promoveazaPilot(){
		Pilot pilot = mock(Pilot.class);
		when(pilot.getOreZbor()).thenReturn(300);
		when(pilot.isCopilot()).thenReturn(true);
		
		assertTrue(Aeroport.getInstance().promoveazaPilot(pilot));
	}
	
	@Test
	public void adaugaAvionCuAcelasiId(){
		Avion a = mock(Avion.class);
		when(a.getId()).thenReturn(55);
		Aeroport.getInstance().addAvion(a);
		
		a = mock(Avion.class);
		when(a.getId()).thenReturn(55);
		assertFalse(Aeroport.getInstance().addAvion(a));
	}
	
	@Test
	public void adaugaPasagerFaraVizaInAvionCuViza() throws NrLocuriNegativExcepion, VolumNegativException{
		Pasager pasager = mock(Pasager.class);
		when(pasager.getViza()).thenReturn(false);
		
		AvionBuilder builder = new AvionBuilder();
		Avion avion = builder.setNrLocuri(500).build();
		
		assertTrue(avion.addPasager(pasager));
	}

}
