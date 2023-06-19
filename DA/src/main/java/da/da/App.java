package da.da;

public class App {
	public static String lichtfeedback(plant p) {
		double progress = p.licht / (p.oplightmax + p.oplightmin);
		String rString = new String();
		int scenario = p.id % 2;
		System.out.println("licht: " + progress);
		if (scenario == 0) {
			if (progress >= 0 && progress < 0.25) {
				rString = "Zu niedrig: Die Lichtintensität ist zu niedrig. Bitte erhöhen Sie die Beleuchtung, um das optimale Wachstum der Pflanzen sicherzustellen.";
			}
			else if (progress >= 0.25 && progress < 0.40) {
				rString = "Niedrig: Die Lichtintensität ist etwas niedrig. Eine leichte Erhöhung der Beleuchtung kann das Wachstum der Pflanzen verbessern.";
			}
			else if (progress >= 0.40 && progress < 0.60) {
				rString = "Optimal: Die Lichtintensität ist optimal. Die Pflanzen erhalten genügend Licht für ihr gesundes Wachstum.";
			}
			else if (progress >= 0.60 && progress < 0.75) {
				rString = "Hoch: Die Lichtintensität ist hoch. Die Pflanzen erhalten ausreichend Licht und können gut gedeihen.";
			}
			else if (progress >= 0.75 && progress <= 1.00) {
				rString = "Zu hoch: Die Lichtintensität ist zu hoch. Reduzieren Sie die Beleuchtung, um ein mögliches Verbrennen der Pflanzen zu vermeiden.";
			}
			else {
				rString = "Keine Feedback";
			}
		}
		else if (scenario == 1) {
			if (progress >= 0 && progress < 0.25) {
				rString = "Zu niedrig: Die Pflanzen leiden unter Lichtmangel. Erhöhen Sie die Beleuchtung, damit sie ausreichend Energie für ihr Wachstum erhalten.";
			}
			else if (progress >= 0.25 && progress < 0.40) {
				rString = "Niedrig: Die Lichtintensität könnte etwas höher sein, um das Pflanzenwachstum zu optimieren.";
			}
			else if (progress >= 0.40 && progress < 0.60) {
				rString = "Optimal: Die Lichtintensität ist ideal für ein gesundes Pflanzenwachstum.";
			}
			else if (progress >= 0.60 && progress < 0.75) {
				rString = "Hoch: Die Lichtintensität ist sehr gut. Die Pflanzen profitieren von ausreichend Licht.";
			}
			else if (progress >= 0.75 && progress <= 1.00) {
				rString = "Zu hoch: Die Lichtintensität ist übermäßig. Verringern Sie die Beleuchtung, um das optimale Pflanzenwachstum zu gewährleisten.";
			}
			else {
				rString = "Keine Feedback";
			}
		}
		return rString;
	}
	public static String feuchtigkeitfeedback(plant p) {
		double progress = p.feuchtigkeit / (p.opfeuchtigkeitmax + p.opfeuchtigkeitmin);
		String rString = new String();
		int scenario = p.id % 2;
		System.out.println("feuch: " + progress);
		if (scenario == 0) {
			if (progress >= 0 && progress < 0.25) {
				rString = "Zu wenig: Der Wassergehalt ist zu niedrig. Bitte gießen Sie die Pflanzen, um sicherzustellen, dass sie ausreichend Feuchtigkeit erhalten.";
			}
			else if (progress >= 0.25 && progress < 0.40) {
				rString = "Niedrig: Der Wassergehalt ist etwas niedrig. Eine leicht erhöhte Bewässerung kann das Pflanzenwachstum unterstützen.";
			}
			else if (progress >= 0.40 && progress < 0.60) {
				rString = "Optimal: Der Wassergehalt ist optimal. Die Pflanzen erhalten die richtige Menge an Wasser für ihr gesundes Wachstum.";
			}
			else if (progress >= 0.60 && progress < 0.75) {
				rString = "Hoch: Der Wassergehalt ist hoch. Die Pflanzen haben genügend Wasser und können gut gedeihen.";
			}
			else if (progress >= 0.75 && progress <= 1.00) {
				rString =  "Zu viel: Der Wassergehalt ist zu hoch. Reduzieren Sie die Bewässerung, um Staunässe zu vermeiden und das Pflanzenwachstum zu verbessern";
			}
			else {
				rString = "Keine Feedback";
			}
		}
		else if (scenario == 1) {
			if (progress >= 0 && progress < 0.25) {
				rString =  "Zu wenig: Die Pflanzen sind dehydriert. Bitte gießen Sie sie großzügig, um den Wasserbedarf zu decken.";
			}
			else if (progress >= 0.25 && progress < 0.40) {
				rString =  "Niedrig: Die Bewässerung sollte etwas erhöht werden, um sicherzustellen, dass die Pflanzen ausreichend Feuchtigkeit erhalten.";
			}
			else if (progress >= 0.40 && progress < 0.60) {
				rString =  "Optimal: Die Lichtintensität ist ideal für ein gesundes Pflanzenwachstum.";
			}
			else if (progress >= 0.60 && progress < 0.75) {
				rString =  "Hoch: Die Lichtintensität ist sehr gut. Die Pflanzen profitieren von ausreichend Licht.";
			}
			else if (progress >= 0.75 && progress <= 1.00) {
				rString =  "Zu hoch: Der Wassergehalt ist übermäßig. Verringern Sie die Bewässerung, um ein mögliches Ertrinken der Pflanzen zu verhindern.";
			}
			else {
				rString =  "Keine Feedback";
			}
		}
		return rString;
	}
	public static String phfeedback(plant p) {
		double progress = p.pH / (p.opphmax + p.opphmin);
		System.out.println("ph: " + progress);
		String rString = new String();
		int scenario = p.id % 2;
		if (scenario == 0) {
			if (progress >= 0 && progress < 0.25) {
				rString = "Zu niedrig: Der pH-Wert ist zu niedrig. Bitte erhöhen Sie den pH-Wert, um ein optimales Wachstum der Pflanzen sicherzustellen.";
			}
			else if (progress >= 0.25 && progress < 0.40) {
				rString = "Niedrig: Der pH-Wert ist etwas niedrig. Eine leichte Erhöhung des pH-Werts kann das Pflanzenwachstum verbessern.";
			}
			else if (progress >= 0.40 && progress < 0.60) {
				rString = "Optimal: Der pH-Wert ist optimal. Die Pflanzen haben den richtigen pH-Wert für ihr gesundes Wachstum.";
			}
			else if (progress >= 0.60 && progress < 0.75) {
				rString = "Hoch: Der pH-Wert ist hoch. Die Pflanzen haben einen ausgewogenen pH-Wert und können gut gedeihen";
			}
			else if (progress >= 0.75 && progress <= 1.00) {
				rString = "Zu hoch: Der pH-Wert ist zu hoch. Reduzieren Sie den pH-Wert, um ein optimales Pflanzenwachstum sicherzustellen.";
			}
			else {
				rString = "Keine Feedback";
			}
		}
		else if (scenario == 1) {
			if (progress >= 0 && progress < 0.25) {
				rString = "Zu niedrig: Der pH-Wert ist stark sauer. Eine Anpassung nach oben ist erforderlich, um ein optimales Pflanzenwachstum zu ermöglichen.";
			}
			else if (progress >= 0.25 && progress < 0.40) {
				rString = "Niedrig: Der pH-Wert neigt zur Säure. Eine leichte Anhebung des pH-Werts kann das Wachstum der Pflanzen verbessern.";
			}
			else if (progress >= 0.40 && progress < 0.60) {
				rString = "Optimal: Der pH-Wert ist im optimalen Bereich und fördert das gesunde Pflanzenwachstum.";
			}
			else if (progress >= 0.60 && progress < 0.75) {
				rString = "Hoch: Der pH-Wert ist ausgeglichen und unterstützt ein gutes Pflanzenwachstum";
			}
			else if (progress >= 0.75 && progress <= 1.00) {
				rString = "Zu hoch: Der pH-Wert ist übermäßig. Verringern Sie den pH-Wert, um ein saures Milieu für die Pflanzen zu vermeiden.";
			}
			else {
				rString = "Keine Feedback";
			}
		}
	return rString;
	}
}