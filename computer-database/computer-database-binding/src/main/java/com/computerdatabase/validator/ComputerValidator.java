package com.computerdatabase.validator;

import java.util.ResourceBundle;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.computerdatabase.dto.ComputerDTO;

@Component
public class ComputerValidator implements Validator{
	
	public boolean verifierDate(String date) {
		// TODO Auto-generated method stub
		
		String regex = ResourceBundle.getBundle("message", LocaleContextHolder.getLocale()).getString("pattern_date.pattern");
		
		return date.matches(regex);
	}

	public boolean verifierName(String name) {
		// TODO Auto-generated method stub
		if (name.length() < 4)
			return false;

		return true;
	}

	public boolean comparerDate(String date1, String date2) {
		// TODO Auto-generated method stub

		String pattern = ResourceBundle.getBundle("message", LocaleContextHolder.getLocale()).getString("pattern.text");
		DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
		
		return formatter.parseLocalDate(date1).isBefore(formatter.parseLocalDate(date2));
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
