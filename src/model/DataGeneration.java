/*

Course: Advanced Programming
Assignment-02
Programmer Name: Rafeed Sultaan
Student ID: s3763175
Copyright: All rights belong to RMIT UNIVERSITY
 */
package model;


/*
 *	DataGeneration class is used to generate 10 random car records
 *	and 5 random vehicles. Each vehicle has at least 2 rental records.
 *	Some of the  vehicles has 3 rental records.
 */
public class DataGeneration {
	/*
	 *	The generateVehicleData method is used to generate 10 random car records
	 *	and 5 random vehicles.
	 */
	public static void generateVehicleData(ThriftyRentSystem trs) {
		Car c = new Car("C_01",2010,"Toyota","Corolla",4,"Car","Available");
		c.setImageName("C_01.jpg");
		trs.getVehiclesList().add(c);
		
		c = new Car("C_02",2008,"Toyota","Prius",4,"Car","Available");
		c.setImageName("C_02.jpg");
		trs.getVehiclesList().add(c);
		
		c = new Car("C_03",2014,"Hyundai","Sonata",4,"Car","Available");
		c.setImageName("C_03.jpg");
		trs.getVehiclesList().add(c);
		
		c = new Car("C_04",2016,"Hyundai","Elantra",4,"Car","Available");
		c.setImageName("C_04.jpg");
		trs.getVehiclesList().add(c);
		
		c = new Car("C_05",2011,"Ford","Focus",4,"Car","Available");
		c.setImageName("C_05.jpg");
		trs.getVehiclesList().add(c);
		

		c = new Car("C_06",2015,"Hummer","H2",7,"Car","Available");
		c.setImageName("C_06.jpg");
		trs.getVehiclesList().add(c);
		

		c = new Car("C_07",2009,"Nissan","Rogue",7,"Car","Available");
		c.setImageName("C_07.jpg");
		trs.getVehiclesList().add(c);
		
		c = new Car("C_08",2014,"Toyota","Rav4",7,"Van","Available");
		c.setImageName("C_08.jpg");
		trs.getVehiclesList().add(c);
		
		

		c = new Car("C_09",2007,"Toyota","Prado",7,"Van","Available");
		c.setImageName("C_09.jpg");
		trs.getVehiclesList().add(c);
		
		c = new Car("C_10",2012,"Mazda","CX-7",7,"Van","Available");
		c.setImageName("C_10.jpg");
		trs.getVehiclesList().add(c);
		
		
		DateTime lmdate = new DateTime(1,1,2019);
		Van v = new Van("V_01",2019,"Toyota","Sienna",15,"Van","Available",lmdate);
		v.setImageName("V_01.jpg");
		trs.getVehiclesList().add(v);
		

		lmdate = new DateTime(2,1,2019);
		v = new Van("V_02",2014,"Honda","Odyssey",15,"Van","Available",lmdate);
		v.setImageName("V_02.jpg");
		trs.getVehiclesList().add(v);
		
		lmdate = new DateTime(3,1,2019);
		v = new Van("V_03",2017,"Ford","Transit",15,"Van","Available",lmdate);
		v.setImageName("V_03.jpg");
		trs.getVehiclesList().add(v);
		
		lmdate = new DateTime(4,1,2019);
		v = new Van("V_04",2012,"Mazda","CX-7",15,"Van","Available",lmdate);
		v.setImageName("V_04.jpg");
		trs.getVehiclesList().add(v);
		
		lmdate = new DateTime(5,1,2019);
		v = new Van("V_05",2013,"Nissan","NV",15,"Van","Available",lmdate);
		v.setImageName("V_05.jpg");
		trs.getVehiclesList().add(v);
	}
	
	/*
	 *	The generateRentalRecordData method is used to generate
	 *	at least 2 rental records for each vehicle.
	 *	Some of the  vehicles have 3 rental records.
	 */
	public static void generateRentalRecordData(ThriftyRentSystem trs) {
		String vid="C_01";
		String customer ="James";
		DateTime rentDate = new DateTime(1,1,2019);
		DateTime estimatedReturnDate = new DateTime(4,1,2019);
		DateTime actualReturnDate = new DateTime(4,1,2019);
		Double rentFee = 78*3.0;
		Double lateFee = 0.00;
		int vehicleKey = trs.searchIndex(vid);
		RentalRecord r1 = new RentalRecord(vid, customer, rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);
	
		vid="C_01";
		customer ="Vishal";
		rentDate = new DateTime(5,1,2019);
		estimatedReturnDate = new DateTime(9,1,2019);
		actualReturnDate = new DateTime(9,1,2019);
		rentFee = 78*4.0;
		lateFee = 0.00;
		vehicleKey = trs.searchIndex(vid);
		r1 = new RentalRecord(vid,customer, rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);
	
		vid="C_02";
		customer ="Dale";
		rentDate = new DateTime(2,1,2019);
		estimatedReturnDate = new DateTime(5,1,2019);
		actualReturnDate = new DateTime(5,1,2019);
		rentFee = 78*3.0;
		lateFee = 0.00;
		vehicleKey = trs.searchIndex(vid);
		r1 = new RentalRecord(vid,customer, rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);
	
	
		vid="C_02";
		customer ="Tao";
		rentDate = new DateTime(6,1,2019);
		estimatedReturnDate = new DateTime(10,1,2019);
		actualReturnDate = new DateTime(10,1,2019);
		rentFee = 78*4.0;
		lateFee = 0.00;
		
		vehicleKey = trs.searchIndex(vid);
		r1 = new RentalRecord(vid,customer, rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);
	
		vid="C_03";
		customer ="Rafeed";
		rentDate = new DateTime(1,1,2019);
		estimatedReturnDate = new DateTime(5,1,2019);
		actualReturnDate = new DateTime(5,1,2019);
		rentFee = 78*4.0;
		lateFee = 0.00;
		vehicleKey = trs.searchIndex(vid);
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);
		
		
		
		vid="C_03";
		customer ="Albert";
		rentDate = new DateTime(6,1,2019);
		estimatedReturnDate = new DateTime(8,1,2019);
		actualReturnDate = new DateTime(8,1,2019);
		rentFee = 78*2.0;
		lateFee = 0.00;
		vehicleKey = trs.searchIndex(vid);
		r1 = new RentalRecord(vid,customer, rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);
		
		vid="C_03";
		customer ="Jane";
		rentDate = new DateTime(9,1,2019);
		estimatedReturnDate = new DateTime(12,1,2019);
		actualReturnDate = new DateTime(13,1,2019);
		rentFee = 78*3.0;
		lateFee = 78*1.25 * 1;
		vehicleKey = trs.searchIndex(vid);
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);
		
		
		vid="C_04";
		customer ="Cole";
		rentDate = new DateTime(1,1,2019);
		estimatedReturnDate = new DateTime(4,1,2019);
		actualReturnDate = new DateTime(5,1,2019);
		rentFee = 78*3.0;
		lateFee = 78*1.25 * 1;
		vehicleKey = trs.searchIndex(vid);
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);
		

		vid="C_05";
		customer ="Thor";
		rentDate = new DateTime(6,1,2019);
		estimatedReturnDate = new DateTime(10,1,2019);
		actualReturnDate = new DateTime(12,1,2019);
		rentFee = 78*4.0;
		lateFee = 78*1.25 * 2;
		vehicleKey = trs.searchIndex(vid);
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);
		

		vid="C_05";
		customer ="Loki";
		rentDate = new DateTime(13,1,2019);
		estimatedReturnDate = new DateTime(16,1,2019);
		actualReturnDate = null;
		rentFee = 0.0;
		lateFee = 0.0;
		vehicleKey = trs.searchIndex(vid);
		String status = "Rented";
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);
		trs.getVehiclesList().get(vehicleKey).setStatus(status);;

		
		vid="C_06";
		customer ="Suchita";
		rentDate = new DateTime(2,1,2019);
		estimatedReturnDate = new DateTime(5,1,2019);
		actualReturnDate = new DateTime(5,1,2019);
		rentFee = 113*3.0;
		lateFee = 0.0;
		vehicleKey = trs.searchIndex(vid);
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);
		
		
		
		
		vid="C_06";
		customer ="Betty";
		rentDate = new DateTime(7,1,2019);
		estimatedReturnDate = new DateTime(9,1,2019);
		actualReturnDate = new DateTime(9,1,2019);
		rentFee = 113*2.0;
		lateFee = 0.0;
		vehicleKey = trs.searchIndex(vid);
		status = "Rented";
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);
		trs.getVehiclesList().get(vehicleKey).setStatus(status);;

		
		
		vid="C_07";
		customer ="Sandy";
		rentDate = new DateTime(2,1,2019);
		estimatedReturnDate = new DateTime(5,1,2019);
		actualReturnDate = new DateTime(5,1,2019);
		rentFee = 113*3.0;
		lateFee = 0.0;
		vehicleKey = trs.searchIndex(vid);
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);
		
		
		
		
		vid="C_07";
		customer ="Bart";
		rentDate = new DateTime(7,1,2019);
		estimatedReturnDate = new DateTime(9,1,2019);
		actualReturnDate = new DateTime(9,1,2019);
		rentFee = 113*2.0;
		lateFee = 0.0;
		vehicleKey = trs.searchIndex(vid);
		status = "Rented";
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);
		trs.getVehiclesList().get(vehicleKey).setStatus(status);;


		

		vid="C_08";
		customer ="Jewel";
		rentDate = new DateTime(1,1,2019);
		estimatedReturnDate = new DateTime(5,1,2019);
		actualReturnDate = new DateTime(6,1,2019);
		rentFee = 113*4.0;
		lateFee = 113*1.25*1;
		vehicleKey = trs.searchIndex(vid);
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);
		
		
		
		
		vid="C_08";
		customer ="Gomes";
		rentDate = new DateTime(7,1,2019);
		estimatedReturnDate = new DateTime(9,1,2019);
		actualReturnDate = new DateTime(9,1,2019);
		rentFee = 113*2.0;
		lateFee = 113*1.25*0;
		vehicleKey = trs.searchIndex(vid);
		status = "Maintenance";
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);
		trs.getVehiclesList().get(vehicleKey).setStatus(status);


		
		vid="C_09";
		customer ="Jewel";
		rentDate = new DateTime(2,1,2019);
		estimatedReturnDate = new DateTime(5,1,2019);
		actualReturnDate = new DateTime(6,1,2019);
		rentFee = 113*3.0;
		lateFee = 113*1.25*1;
		vehicleKey = trs.searchIndex(vid);
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);
		
		
		
		
		vid="C_09";
		customer ="Gomes";
		rentDate = new DateTime(7,1,2019);
		estimatedReturnDate = new DateTime(9,1,2019);
		actualReturnDate = new DateTime(9,1,2019);
		rentFee = 113*2.0;
		lateFee = 113*1.25*0;
		vehicleKey = trs.searchIndex(vid);
		status = "Maintenance";
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);
		trs.getVehiclesList().get(vehicleKey).setStatus(status);

		
		
		vid="C_10";
		customer ="Juan";
		rentDate = new DateTime(2,1,2019);
		estimatedReturnDate = new DateTime(5,1,2019);
		actualReturnDate = new DateTime(6,1,2019);
		rentFee = 113*3.0;
		lateFee = 113*1.25*1;
		vehicleKey = trs.searchIndex(vid);
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);
		
		
		
		
		vid="C_10";
		customer ="Pedro";
		rentDate = new DateTime(7,1,2019);
		estimatedReturnDate = new DateTime(9,1,2019);
		actualReturnDate = new DateTime(9,1,2019);
		rentFee = 113*2.0;
		lateFee = 113*1.25*0;
		vehicleKey = trs.searchIndex(vid);
		status = "Maintenance";
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);
		trs.getVehiclesList().get(vehicleKey).setStatus(status);


		
		
		
		vid="V_01";
		customer ="Sanchez";
		rentDate = new DateTime(3,1,2019);
		estimatedReturnDate = new DateTime(6,1,2019);
		actualReturnDate = new DateTime(6,1,2019);
		rentFee = 235.0*3;
		lateFee = 299.0*0;
		vehicleKey = trs.searchIndex(vid);
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);

		
		
		vid="V_01";
		customer ="Ruma";
		rentDate = new DateTime(7,1,2019);
		estimatedReturnDate = new DateTime(10,1,2019);
		actualReturnDate = new DateTime(11,1,2019);
		rentFee = 235.0*3;
		lateFee = 299.0*1;
		vehicleKey = trs.searchIndex(vid);
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);

		vid="V_02";
		customer ="Jimmy";
		rentDate = new DateTime(2,1,2019);
		estimatedReturnDate = new DateTime(3,1,2019);
		actualReturnDate = new DateTime(3,1,2019);
		rentFee = 235.0*3;
		lateFee = 299.0*0;
		vehicleKey = trs.searchIndex(vid);
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);

		vid="V_02";
		customer ="Joe";
		rentDate = new DateTime(4,1,2019);
		estimatedReturnDate = new DateTime(6,1,2019);
		actualReturnDate = new DateTime(7,1,2019);
		rentFee = 235.0*2;
		lateFee = 299.0*1;
		vehicleKey = trs.searchIndex(vid);
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);

		vid="V_02";
		customer ="Sue";
		rentDate = new DateTime(8,1,2019);
		estimatedReturnDate = new DateTime(10,1,2019);
		actualReturnDate = null;
		rentFee = 0.0;
		lateFee = 0.0;
		status = "Rented";
		vehicleKey = trs.searchIndex(vid);
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);
		trs.getVehiclesList().get(vehicleKey).setStatus(status);
		
		vid="V_03";
		customer ="Alison";
		rentDate = new DateTime(2,1,2019);
		estimatedReturnDate = new DateTime(3,1,2019);
		actualReturnDate = new DateTime(3,1,2019);
		rentFee = 235.0*3;
		lateFee = 299.0*0;
		vehicleKey = trs.searchIndex(vid);
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);

		vid="V_03";
		customer ="Cersei";
		rentDate = new DateTime(4,1,2019);
		estimatedReturnDate = new DateTime(6,1,2019);
		actualReturnDate = new DateTime(7,1,2019);
		rentFee = 235.0*2;
		lateFee = 299.0*1;
		vehicleKey = trs.searchIndex(vid);
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);

		vid="V_03";
		customer ="Smith";
		rentDate = new DateTime(8,1,2019);
		estimatedReturnDate = new DateTime(10,1,2019);
		actualReturnDate = null;
		rentFee = 0.0;
		lateFee = 0.0;
		status = "Rented";
		vehicleKey = trs.searchIndex(vid);
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);
		trs.getVehiclesList().get(vehicleKey).setStatus(status);
		
		vid="V_04";
		customer ="Happy";
		rentDate = new DateTime(2,1,2019);
		estimatedReturnDate = new DateTime(4,1,2019);
		actualReturnDate = new DateTime(4,1,2019);
		rentFee = 235.0*2;
		lateFee = 299.0*0;
		vehicleKey = trs.searchIndex(vid);
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);

		vid="V_04";
		customer ="Singh";
		rentDate = new DateTime(5,1,2019);
		estimatedReturnDate = new DateTime(6,1,2019);
		actualReturnDate = new DateTime(7,1,2019);
		rentFee = 235.0*1;
		lateFee = 299.0*1;
		vehicleKey = trs.searchIndex(vid);
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);

		vid="V_04";
		customer ="Honey";
		rentDate = new DateTime(8,1,2019);
		estimatedReturnDate = new DateTime(11,1,2019);
		actualReturnDate = new DateTime(11,1,2019);
		rentFee = 235.0*3;
		lateFee = 299.0*0;
		status = "Maintenance";
		vehicleKey = trs.searchIndex(vid);
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);
		trs.getVehiclesList().get(vehicleKey).setStatus(status);

		
		vid="V_05";
		customer ="Elisa";
		rentDate = new DateTime(2,1,2019);
		estimatedReturnDate = new DateTime(4,1,2019);
		actualReturnDate = new DateTime(4,1,2019);
		rentFee = 235.0*2;
		lateFee = 299.0*0;
		vehicleKey = trs.searchIndex(vid);
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);

		vid="V_05";
		customer ="Cruise";
		rentDate = new DateTime(5,1,2019);
		estimatedReturnDate = new DateTime(6,1,2019);
		actualReturnDate = new DateTime(7,1,2019);
		rentFee = 235.0*1;
		lateFee = 299.0*1;
		vehicleKey = trs.searchIndex(vid);
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);

		vid="V_05";
		customer ="Tom";
		rentDate = new DateTime(8,1,2019);
		estimatedReturnDate = new DateTime(11,1,2019);
		actualReturnDate = new DateTime(11,1,2019);
		rentFee = 235.0*3;
		lateFee = 299.0*0;
		status = "Maintenance";
		vehicleKey = trs.searchIndex(vid);
		r1 = new RentalRecord(vid,customer,rentDate, estimatedReturnDate,actualReturnDate,rentFee,lateFee);
		trs.getVehiclesList().get(vehicleKey).addRentalRecord(r1);
		trs.getVehiclesList().get(vehicleKey).setStatus(status);

		
	}
	
	
}
