package com.computerdatabase.validator;

import java.util.HashMap;
import java.util.Map;

import com.computerdatabase.dto.ComputerDTO;

public class ComputerValidator {

	private Map<String, String> tableau;
	
	private static ComputerValidator computerValidator =null;

	public ComputerValidator() {
		tableau = new HashMap<String, String>();
	}
	
	public static ComputerValidator getInstance()
	{
		if(computerValidator == null)
			computerValidator = new ComputerValidator();
		
		return computerValidator;
	}

	public Map<String, String> getTableau() {
		return tableau;
	}

	public void setTableau(Map<String, String> tableau) {
		this.tableau = tableau;
	}

	public void test(ComputerDTO computerDTO) {
		
		tableau.clear();
		
		if (computerDTO.getDiscontinuedDate() != null) {
			if (!verifierDate(computerDTO.getDiscontinuedDate())) {
				errorDateDiscontinued();
				computerDTO.setDiscontinuedDate(null);
			}

		}

		if (computerDTO.getIntroducedDate() != null) {
			if (!verifierDate(computerDTO.getIntroducedDate())) {
				errorDateIntroduced();
				computerDTO.setIntroducedDate(null);
			}
		}

		if (computerDTO.getIntroducedDate() != null
				&& computerDTO.getDiscontinuedDate() != null) {
			if (!comparerDate(computerDTO.getIntroducedDate(),
					computerDTO.getDiscontinuedDate())) {
				errorCompareDate();
			}
		}

		if (!verifierName(computerDTO.getNom())) {
			errorName();
			computerDTO.setNom(null);
		}

	}

	public void errorCompareDate() {
		
		tableau.put("introducedDate", "Introduced date > Discontinued date");
		tableau.put("discontinuedDate", "Introduced date > Discontinued date");

	}

	public void errorName() {
		tableau.put("name", "Error in the name");
	}

	public void errorDateIntroduced() {
		tableau.put("introducedDate", "Error in the introduced date");
	}

	public void errorDateDiscontinued() {
		tableau.put("discontinuedDate", "Error in the discontinued date");
	}

	public void errorCompany() {
		tableau.put("company", "Error in the company");
	}

	public boolean verifierDate(String date) {
		// TODO Auto-generated method stub

		boolean bissextile = false;

		String[] tableDate = date.split("-");

		int annee = Integer.parseInt(tableDate[0]);
		int mois = Integer.parseInt(tableDate[1]);
		int jour = Integer.parseInt(tableDate[2]);

		if (annee % 400 == 0)
			bissextile = true;
		else if (annee % 4 == 0 && annee % 100 != 0)
			bissextile = true;

		int tableJour[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		if (bissextile)
			tableJour[1] = 29;

		if (tableJour[mois - 1] >= jour)
			return true;

		return false;
	}

	public boolean verifierName(String name) {
		// TODO Auto-generated method stub

		if (name.length() < 4)
			return false;

		return true;
	}

	public boolean comparerDate(String date1, String date2) {
		// TODO Auto-generated method stub

		String[] tableDate = date1.split("-");

		int annee = Integer.parseInt(tableDate[0]);
		int mois = Integer.parseInt(tableDate[1]);
		int jour = Integer.parseInt(tableDate[2]);

		String[] tableDate2 = date2.split("-");

		int annee2 = Integer.parseInt(tableDate2[0]);
		int mois2 = Integer.parseInt(tableDate2[1]);
		int jour2 = Integer.parseInt(tableDate2[2]);

		if (annee > annee2)
			return false;
		else if (annee == annee2) {
			if (mois > mois2)
				return false;
			else if (mois == mois2) {
				if (jour > jour2)
					return false;
			}
		}

		return true;
	}

}
