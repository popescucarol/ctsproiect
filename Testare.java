package testare;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import obiecte.Avion;
import obiecte.Avion.Marca;
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

public class Testare {
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
	public void testAvionBuilder() throws NrLocuriNegativExcepion, VolumNegativException {
		Avion a1 = new Avion(Marca.Airbus, 100, null, false);
		
		AvionBuilder builder = new AvionBuilder();
		Avion a2 = builder.setMarca(Marca.Airbus).setNrLocuri(100).setNecesitaViza(false).build();
		a2.setId(a1.getId());
		
		System.out.println(a1);
		System.out.println(a2);
		
		assertEquals(a1, a2);
	}
	
	@Test
	public void testIdAvioane() throws NrLocuriNegativExcepion, VolumNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion a1 = builder.setMarca(Marca.Airbus).setNrLocuri(100).setNecesitaViza(true).build();
		builder = new AvionBuilder();
		Avion a2 = builder.setMarca(Marca.Airbus).setNrLocuri(20).setNecesitaViza(true).build();
		 builder = new AvionBuilder();
		Avion a3 = builder.setMarca(Marca.IAR).setNrLocuri(400).setNecesitaViza(false).build();
		builder = new AvionBuilder();
		Avion a4 = builder.setMarca(Marca.Airbus).setNrLocuri(100).setNecesitaViza(true).build();
		
		assertEquals(4, a4.getId());
	}
	
	@Test
	public void testCreareListaPasageri() throws NrLocuriNegativExcepion, VolumNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion a1 = builder.setMarca(Marca.Airbus).setNrLocuri(80).setNecesitaViza(true).build();
		
		assertNotNull(a1.getListaPasageri());
	}

	@Test
	public void testCreareListaPasageri2() throws NrLocuriNegativExcepion, VolumNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion a1 = builder.setMarca(Marca.Boeing).setNrLocuri(0).setNecesitaViza(true).build();
		
		assertNull(a1.getListaPasageri());
	}
	
	@Test
	public void testSetPilotPilot() throws NumarOreNegativException, NrLocuriNegativExcepion, VolumNegativException{
		Pilot pilot = new Pilot("Spaima cerurilor", 200, false);
		AvionBuilder builder = new AvionBuilder();
		Avion a1 = builder.setMarca(Marca.IAR).setNrLocuri(50).setNecesitaViza(true).build();
		
		assertTrue(a1.setPilot(pilot));
	}
	
	@Test
	public void testSetPilotCopilot() throws NumarOreNegativException, NrLocuriNegativExcepion, VolumNegativException{
		Pilot pilot = new Pilot("Neghinita", 50, true);
		
		AvionBuilder builder = new AvionBuilder();
		Avion a1 = builder.setMarca(Marca.IAR).setNrLocuri(50).setNecesitaViza(false).build();
		
		assertFalse(a1.setPilot(pilot));
	}
	
	@Test
	public void testSortareAvioaneLocuriDesc() throws NrLocuriNegativExcepion, VolumNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion a1 = builder.setNrLocuri(500).build();
		Aeroport.getInstance().addAvion(a1);
		
		builder = new AvionBuilder();
		Avion a2 = builder.setNrLocuri(600).build();
		Aeroport.getInstance().addAvion(a2);
		
		builder = new AvionBuilder();
		Avion a3 = builder.setNrLocuri(300).build();	
		Aeroport.getInstance().addAvion(a3);
		
		builder = new AvionBuilder();
		Avion a4 = builder.setNrLocuri(800).build();
		Aeroport.getInstance().addAvion(a4);
		
		ArrayList<Avion> listaCorecta = new ArrayList<Avion>();
		listaCorecta.add(a4);
		listaCorecta.add(a2);
		listaCorecta.add(a1);
		listaCorecta.add(a3);
		
		assertEquals(listaCorecta, Aeroport.getInstance().sortLocuriDesc());
		
	}

	@Test
	public void testSortareAvioaneLocuriAsc() throws NrLocuriNegativExcepion, VolumNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion a1 = builder.setNrLocuri(500).build();
		Aeroport.getInstance().addAvion(a1);
		
		builder = new AvionBuilder();
		Avion a2 = builder.setNrLocuri(600).build();
		Aeroport.getInstance().addAvion(a2);
		
		builder = new AvionBuilder();
		Avion a3 = builder.setNrLocuri(300).build();
		Aeroport.getInstance().addAvion(a3);
		
		builder = new AvionBuilder();
		Avion a4 = builder.setNrLocuri(800).build();
		Aeroport.getInstance().addAvion(a4);
		
		ArrayList<Avion> listaCorecta = new ArrayList<Avion>();
		listaCorecta.add(a3);
		listaCorecta.add(a1);
		listaCorecta.add(a2);
		listaCorecta.add(a4);
		
		assertEquals(listaCorecta, Aeroport.getInstance().sortLocuriAsc());
		
	}
	
	
	@Test
	public void testSortareAvioaneVolumDesc() throws NrLocuriNegativExcepion, VolumNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion a1 = builder.setVolum(300).build();
		Aeroport.getInstance().addAvion(a1);
		
		builder = new AvionBuilder();
		Avion a2 = builder.setVolum(500).build();
		Aeroport.getInstance().addAvion(a2);
		
		builder = new AvionBuilder();
		Avion a3 = builder.setVolum(100).build();
		Aeroport.getInstance().addAvion(a3);
		
		ArrayList<Avion> listaCorecta = new ArrayList<Avion>();
		listaCorecta.add(a2);
		listaCorecta.add(a1);
		listaCorecta.add(a3);
		
		assertEquals(listaCorecta, Aeroport.getInstance().sortVolumDesc());
		
	}
	
	
	@Test
	public void testSortareAvioaneVolumAsc() throws NrLocuriNegativExcepion, VolumNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion a1 = builder.setVolum(300).build();
		Aeroport.getInstance().addAvion(a1);
		
		builder = new AvionBuilder();
		Avion a2 = builder.setVolum(500).build();
		Aeroport.getInstance().addAvion(a2);
		
		builder = new AvionBuilder();
		Avion a3 = builder.setVolum(100).build();
		Aeroport.getInstance().addAvion(a3);
		
		ArrayList<Avion> listaCorecta = new ArrayList<Avion>();
		listaCorecta.add(a3);
		listaCorecta.add(a1);
		listaCorecta.add(a2);
		
		assertEquals(listaCorecta, Aeroport.getInstance().sortVolumAsc());
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testAdaugaPasagerLaAvionCargo() throws NrLocuriNegativExcepion, VolumNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion a1 = builder.build();
		
		a1.addPasager(new Pasager("Adi", true));
	}
	
	@Test
	public void testAdaugaPasagerLaAvionPasageri() throws NrLocuriNegativExcepion, VolumNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion a1 = builder.setNrLocuri(500).build();
		
		a1.addPasager(new Pasager("Adi", true));
	}
	
	@Test
	public void testRemoveAvionReusit() throws NrLocuriNegativExcepion, VolumNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion a1 = builder.build();
		Aeroport.getInstance().addAvion(a1);
		assertTrue(Aeroport.getInstance().removeAvion(a1.getId()));
	}
	
	@Test
	public void testRemoveAvionNereusit(){
		assertFalse(Aeroport.getInstance().removeAvion(50));
	}
	
	@Test
	public void testRemoveAvionNereusit2() throws NrLocuriNegativExcepion, VolumNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion a1 = builder.build();
		Aeroport.getInstance().addAvion(a1);
		
		builder = new AvionBuilder();
		a1 = builder.build();
		Aeroport.getInstance().addAvion(a1);
		
		builder = new AvionBuilder();
		a1 = builder.build();
		Aeroport.getInstance().addAvion(a1);
		
		assertFalse(Aeroport.getInstance().removeAvion(50));
	}
	
	@Test
	public void testClone() throws NrLocuriNegativExcepion, VolumNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion a1 = builder.build();
		
		Avion a2 = a1.clone();
		
		assertEquals(a1, a2);
	}
	
	@Test
	public void testAvionToString() throws NrLocuriNegativExcepion, VolumNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion a1 = builder.setMarca(Marca.Airbus).build();
		
		assertEquals("Avionul cu "+a1.getId()+" este de tip "+a1.getMarca(), a1.toString());
	}
	
	@Test
	public void testSingleton(){
		Aeroport aeroport = Aeroport.getInstance();
		assertEquals(aeroport, Aeroport.getInstance());
	}
	
	@Test(expected=NuExistaPilotSauCopilotException.class)
	public void avionNuPoateDecolaFaraPilotSiCopilot() throws NuExistaPilotSauCopilotException, NrLocuriNegativExcepion, VolumNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion a1 = builder.build();
		
		a1.decoleaza();
	}
	
	@Test(expected=NuExistaPilotSauCopilotException.class)
	public void avionNuPoateDecolaFaraPilot() throws NuExistaPilotSauCopilotException, NumarOreNegativException, NrLocuriNegativExcepion, VolumNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion a1 = builder.build();
		a1.setCopilot(new Pilot("Iussuf", 10, true));
		
		a1.decoleaza();
	}
	
	@Test(expected=NuExistaPilotSauCopilotException.class)
	public void avionNuPoateDecolaFaraCopilot() throws NuExistaPilotSauCopilotException, NumarOreNegativException, NrLocuriNegativExcepion, VolumNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion a1 = builder.build();
		a1.setPilot(new Pilot("Manuel", 1000, false));
		
		a1.decoleaza();
	}
	
	@Test
	public void avionDecoleaza() throws NuExistaPilotSauCopilotException, NumarOreNegativException, NrLocuriNegativExcepion, VolumNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion a1 = builder.build();
		a1.setPilot(new Pilot("Manuel", 1000, false));
		a1.setCopilot(new Pilot("Iussuf", 10, true));
		
		a1.decoleaza();
	}
	
	@Test(expected=NumarOreNegativException.class)
	public void testPilotNrOreNegativ() throws NumarOreNegativException{
		Pilot p = new Pilot("Azorel", -14, false);
	}
	
	@Test(expected=NrLocuriNegativExcepion.class)
	public void testNrLocuriNegativ() throws NrLocuriNegativExcepion, VolumNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion a1 = builder.setNrLocuri(-15).build();
	}
	
	@Test(expected=VolumNegativException.class)
	public void testVolumNegativ() throws NrLocuriNegativExcepion, VolumNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion a1 = builder.setVolum(-300).build();
	}
	
	@Test
	public void testGetListaAvioane(){
		assertNotNull(Aeroport.getInstance().getListaAvioane());
	}
	
	@Test
	public void testAdaugareAvionListaAvioane() throws NrLocuriNegativExcepion, VolumNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion avion = builder.build();
		Aeroport.getInstance().addAvion(avion);
		
		builder = new AvionBuilder();
		avion = builder.build();
		Aeroport.getInstance().addAvion(avion);
		
		assertEquals(2, Aeroport.getInstance().getListaAvioane().size());
	}
	
	@Test
	public void testAdaugaPasagerLaUnAvionFullFalse() throws NrLocuriNegativExcepion, VolumNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion avion = builder.setNrLocuri(4).build();
		
		Pasager pasager = new Pasager("Ion", false);
		avion.addPasager(pasager);
		pasager = new Pasager("Andrei", false);
		avion.addPasager(pasager);
		pasager = new Pasager("Ghita", true);
		avion.addPasager(pasager);
		pasager = new Pasager("Iulica", false);
		avion.addPasager(pasager);
		pasager = new Pasager("Fabiana", true);
		assertFalse(avion.addPasager(pasager));
	}
	
	@Test
	public void testAdaugaPasagerLaUnAvionFullTrue() throws NrLocuriNegativExcepion, VolumNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion avion = builder.setNrLocuri(4).build();
		
		Pasager pasager = new Pasager("Ion", false);
		avion.addPasager(pasager);
		pasager = new Pasager("Andrei", false);
		avion.addPasager(pasager);
		pasager = new Pasager("Ghita", true);
		avion.addPasager(pasager);
		pasager = new Pasager("Iulica", false);
		assertTrue(avion.addPasager(pasager));
	}
	
	@Test
	public void testIdPilot() throws NumarOreNegativException{
		Pilot pilot = new Pilot("Vlaicu", 22, false);
		assertEquals(1, pilot.getId());
	}
	
	@Test
	public void testIdPilot2() throws NumarOreNegativException{
		Pilot pilot = new Pilot("Vlaicu", 22, false);
		pilot = new Pilot("Adi", 11, false);
		pilot = new Pilot("IUliana", 33, false);
		pilot = new Pilot("Francisca", 55, false);
		assertEquals(4, pilot.getId());
	}
	
	@Test
	public void testIdPasager(){
		Pasager pasager = new Pasager("El", true);
		assertEquals(1, pasager.getId());
	}
	
	@Test
	public void testIdPasager2(){
		Pasager pasager = new Pasager("El", true);
		pasager = new Pasager("Ea", true);
		assertEquals(2, pasager.getId());
	}
	
	@Test
	public void testAvionAterizareReusita() throws NrLocuriNegativExcepion, VolumNegativException, NuExistaPilotSauCopilotException, AvionulNuADecolatException, NumarOreNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion avion = builder.build();
		avion.setPilot(new Pilot("A", 300, false));
		avion.setCopilot(new Pilot("B", 100, true));
		
		avion.decoleaza();
		avion.aterizeaza();
	}
	
	@Test(expected=AvionulNuADecolatException.class)
	public void testAvionAterizareImposibila() throws NrLocuriNegativExcepion, VolumNegativException, NuExistaPilotSauCopilotException, AvionulNuADecolatException, NumarOreNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion avion = builder.build();
		avion.setPilot(new Pilot("A", 300, false));
		avion.setCopilot(new Pilot("B", 100, true));
		
		avion.aterizeaza();
	}
	
	@Test
	public void testAvionGetMarca() throws NrLocuriNegativExcepion, VolumNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion avion = builder.setMarca(Marca.Boeing).build();
		
		assertEquals(Marca.Boeing, avion.getMarca());
	}
	
	@Test
	public void testGetPilotDupaInitializareNull() throws NrLocuriNegativExcepion, VolumNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion avion = builder.build();
		
		assertNull(avion.getPilot());
	}
	
	@Test
	public void testGetCopilotDupaInitializareNull() throws NrLocuriNegativExcepion, VolumNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion avion = builder.build();
		
		assertNull(avion.getCopilot());
	}
	
	
	@Test
	public void testGetPilotDupaSetarePilotNotNull() throws NrLocuriNegativExcepion, VolumNegativException, NumarOreNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion avion = builder.build();
		
		avion.setPilot(new Pilot("Pilotul", 11, false));
		
		assertNotNull(avion.getPilot());
	}
	
	@Test
	public void testGetCopilotDupaSetarePilotNotNull() throws NrLocuriNegativExcepion, VolumNegativException, NumarOreNegativException{
		AvionBuilder builder = new AvionBuilder();
		Avion avion = builder.build();
		
		avion.setCopilot(new Pilot("Pilotul", 10, true));
		
		assertNotNull(avion.getCopilot());
	}
	
	@Test
	public void testGetPasagerNumeNotNull(){
		Pasager pasager = new Pasager("Adi", false);
		
		assertNotNull(pasager.getNume());
	}
	
	@Test
	public void testGetPasagerVizaNotNull(){
		Pasager pasager = new Pasager("Adi", false);
		
		assertNotNull(pasager.getViza());
	}
	
	@Test
	public void testGetPilotNumeNotNull() throws NumarOreNegativException{
		Pilot pilot = new Pilot("Asd", 11, false);
		
		assertNotNull(pilot.getNume());
	}
	
	@Test
	public void testGetPilotOreZborNotNull() throws NumarOreNegativException{
		Pilot pilot = new Pilot("BBB", 333, false);
		
		assertNotNull(pilot.getOreZbor());
	}
	
	@Test
	public void testIsPilotCopilotNotNull() throws NumarOreNegativException{
		Pilot pilot = new Pilot("CCC", 212, true);
		
		assertNotNull(pilot.isCopilot());
	}
	
	@Test
	public void testPasagerGetNumeNotNull(){
		Pasager pasager = new Pasager("VVV", true);
		
		assertNotNull(pasager.getNume());
	}
	
	@Test
	public void testPasagerGetVizaNotNull(){
		Pasager pasager = new Pasager("TTAS", true);
		
		assertNotNull(pasager.getViza());
	}
	
}
