package br.edu.insper.desagil.alfandega;

import java.util.ArrayList;
import java.util.List;

public class Alfandega {
	private List<Item> itens;
	private List<ItemTarifado> itensTarifados;

	public Alfandega() {
		this.itens = new ArrayList<>();
		this.itensTarifados = new ArrayList<>();
	}

	public void declara(Item item) {
		this.itens.add(item);
	}

	public void declara(ItemTarifado itemTarifado) {
		this.itensTarifados.add(itemTarifado);
	}
	
	public double calculaDeclarado(double total, boolean tarifado) {
		if (tarifado == true) {
			for (ItemTarifado itemTarifado : this.itensTarifados) {
				total += itemTarifado.getRate() * itemTarifado.getValor();
		}
		} else {
			for (Item item : this.itens) {
				total += item.getRate() * item.getValor();
			}
		}
		return total;
	}
	
	public double calculaDevido(double total, boolean tarifado) {
		if (tarifado == true) {
			for (ItemTarifado itemTarifado : this.itensTarifados) {
				total += itemTarifado.getRate() * itemTarifado.getValor() * itemTarifado.getTarifa();
		}
		} else {
			total += calculaDeclarado(total, false)*0.01;
		}
		return total;
	}
	
	public double getTotalDeclarado() {
		double total = 0.0;
		double totalSemTarifa = calculaDeclarado(total, false);
		double totalComTarifa = calculaDeclarado(total, true);
		total = totalSemTarifa + totalComTarifa;
		return total;
	}

	public double getTotalDevido() {
		double total = 0.0;
		double totalSemTarifa = calculaDevido(total, false);
		double totalComTarifa = calculaDevido(total, true);
		total = totalSemTarifa + totalComTarifa;
		return total;
	}
}
