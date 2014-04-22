package com.computerdatabase.orderspecifier;

import com.computerdatabase.domain.QCompany;
import com.computerdatabase.domain.QComputer;
import com.computerdatabase.wrapper.Page;
import com.mysema.query.types.Expression;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.path.PathBuilder;

public class OrderSpecifierImpl {
	
	public static OrderSpecifier retrieveOrderSpecification(Page page, QCompany company, QComputer computer){
	
		PathBuilder orderByExpression = new PathBuilder(Object.class, "object");

		return new OrderSpecifier(
				(page.getOrdre().equals("asc")) ? com.mysema.query.types.Order.ASC
						: com.mysema.query.types.Order.DESC, returnExpression(page.getSort(), company,
								computer));

	}
	
	public static Expression returnExpression(String sort, QCompany company,
			QComputer computer) {
		Expression expression = null;
		
		switch (sort) {
		case "company.name":
			expression = company.name;
			break;
		case "computer.name":
			expression = computer.name;
			break;
		case "computer.introducedDate":
			expression = computer.introducedDate;
			break;
		case "computer.discontinuedDate":
			expression = computer.discontinuedDate;
			break;
		default:
			expression = computer.name;
			break;

		}

		return expression;
	}

}
