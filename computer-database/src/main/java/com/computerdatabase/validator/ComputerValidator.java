package com.computerdatabase.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.computerdatabase.dto.ComputerDTO;

@Component
public class ComputerValidator implements Validator{

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

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return ComputerDTO.class.equals(arg0);
	}

	@Override
	public void validate(Object obj, Errors e) {
		// TODO Auto-generated method stub
		ComputerDTO computerDTO = (ComputerDTO) obj;
		

		if (computerDTO.getDiscontinuedDate() != null && computerDTO.getDiscontinuedDate().compareTo("") != 0) {
			if (!verifierDate(computerDTO.getDiscontinuedDate())) {
				e.rejectValue("discontinuedDate", "error.discon", "Error in the discontinued date");
				computerDTO.setDiscontinuedDate(null);
			}

		}

		if (computerDTO.getIntroducedDate() != null && computerDTO.getIntroducedDate().compareTo("") != 0) {
			if (!verifierDate(computerDTO.getIntroducedDate())) {
				e.rejectValue("introducedDate", "error.intro", "Error in the introduced date");
				computerDTO.setIntroducedDate(null);
			}
		}

		if (computerDTO.getIntroducedDate() != null
				&& computerDTO.getDiscontinuedDate() != null && computerDTO.getDiscontinuedDate().compareTo("") != 0 && computerDTO.getIntroducedDate().compareTo("") != 0) {
			if (!comparerDate(computerDTO.getIntroducedDate(),
					computerDTO.getDiscontinuedDate())) {
				e.rejectValue("introducedDate", "error.compare", "Introduced date > Discontinued date");
				e.rejectValue("discontinuedDate", "error.compare", "Introduced date > Discontinued date");
			}
		}

		if (!verifierName(computerDTO.getName())) {
			e.rejectValue("name", "error.name", "Error in the name");
			computerDTO.setName(null);
		}

	}

}
