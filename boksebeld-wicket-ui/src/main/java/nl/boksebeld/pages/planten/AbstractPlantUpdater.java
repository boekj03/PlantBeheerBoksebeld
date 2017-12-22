package nl.boksebeld.pages.planten;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.validation.validator.StringValidator;

import nl.boksebeld.applicatie.web.MasterPage;
import nl.boksebeld.domein.plant.Plant;
import nl.boksebeld.pages.planten.checkbox.BloeitijdCheckBoxChoice;
import nl.boksebeld.pages.planten.dropdownchoice.BladhoudenDropDownChoice;
import nl.boksebeld.pages.planten.dropdownchoice.GrondsoortDropDownChoice;
import nl.boksebeld.pages.planten.dropdownchoice.HoogteDropDownChoice;
import nl.boksebeld.pages.planten.dropdownchoice.KleurDropDownChoice;
import nl.boksebeld.pages.planten.dropdownchoice.LichtbehoefteDropDownChoice;
import nl.boksebeld.pages.planten.dropdownchoice.PlantsoortDropDownChoice;

public abstract class AbstractPlantUpdater extends MasterPage {

	private FileUploadField fileUpload;
	/**
	 * 
	 */
	private static final long serialVersionUID = -1847922306605479537L;

	@SuppressWarnings("serial")
	public final class MaakPlantForm extends Form<Plant> {

		public MaakPlantForm(final String id, IModel<Plant> plantModel) {
			super(id, plantModel);

			// naw gegevens
			plantgegevens(plantModel);
			add(backLink("backLink"));
		}

		private void plantgegevens(final IModel<Plant> plantModel) {

			TextField<String> botanischeNaam = new TextField<String>("botanischeNaam");
			botanischeNaam.setRequired(true);
			botanischeNaam.add(StringValidator.maximumLength(40));
			add(botanischeNaam);

			TextField<String> code = new TextField<String>("code");
			code.setRequired(true);
			code.add(StringValidator.maximumLength(10));
			add(code);

			TextField<String> nederlandseNaam = new TextField<String>("nederlandseNaam");
			nederlandseNaam.setRequired(true);
			nederlandseNaam.add(StringValidator.maximumLength(30));
			add(nederlandseNaam);

			TextArea<String> beschrijving = new TextArea<String>("beschrijving");
			beschrijving.setRequired(true);
			beschrijving.add(StringValidator.maximumLength(800));
			add(beschrijving);

			HoogteDropDownChoice hoogteDdc = new HoogteDropDownChoice("hoogte");
			add(hoogteDdc);

			BloeitijdCheckBoxChoice bloeitijdCb = new BloeitijdCheckBoxChoice("bloeitijdLijst");
			add(bloeitijdCb);
			// hier nieuwe velden
			TextField<String> leverancier = new TextField<String>("leverancier");
			leverancier.setRequired(false);
			leverancier.add(StringValidator.maximumLength(30));
			add(leverancier);

			TextField<String> handelsmaat = new TextField<String>("handelsmaat");
			handelsmaat.setRequired(false);
			handelsmaat.add(StringValidator.maximumLength(30));
			add(handelsmaat);

			TextField<Double> inkoopprijs = new TextField<Double>("inkoopprijs");
			inkoopprijs.setRequired(false);
			add(inkoopprijs);

			GrondsoortDropDownChoice grondsoortDdc = new GrondsoortDropDownChoice("grondsoort");
			add(grondsoortDdc);
			KleurDropDownChoice kleurDdc = new KleurDropDownChoice("kleur");
			add(kleurDdc);
			LichtbehoefteDropDownChoice lichtbehoefteDdc = new LichtbehoefteDropDownChoice("lichtbehoefte");
			add(lichtbehoefteDdc);
			PlantsoortDropDownChoice plantsoortDdc = new PlantsoortDropDownChoice("plantsoort");
			add(plantsoortDdc);

			BladhoudenDropDownChoice bladhoudendDdc = new BladhoudenDropDownChoice("bladhoudend");
			add(bladhoudendDdc);

			IResource imageResource = getPlantImage();
			add(new Image("image", imageResource));

			add(new Button("mainsubmit") {
				@Override
				public final void onSubmit() {

					Plant plant = plantModel.getObject();

					werkPlantenLijstBij(plant);

					// Pass success message to next page:
					getSession().info("The plant '" + plant.getNederlandseNaam() + "' was saved!");
					setResponsePage(new PlantenPagina());

				}
			});

			// uploadpicture
			Form<?> form = getForm(plantModel.getObject());
			// Enable multipart mode (need for uploads file)
			form.setMultiPart(true);
			// max upload size, 10k
			form.setMaxSize(Bytes.kilobytes(40));

			// form.
			// Add a create Contact form to the pageform.
			form.add(fileUpload = new FileUploadField("fileUpload", new Model()));
			form.add(new Button("uploadsubmit") {

				@Override
				public void onSubmit() {
					Plant plant = plantModel.getObject();
					final FileUpload uploadedFile = fileUpload.getFileUpload();
					if (uploadedFile != null) {
						try {
							byte[] bFile = uploadedFile.getBytes();
							plant.setImage(bFile);

						} catch (Exception e) {
							throw new IllegalStateException("Error");
						}
					}

				}
			});

			add(form);

		}

		private IResource getPlantImage() {
			IResource imageResource = new DynamicImageResource() {
				@Override
				protected byte[] getImageData(IResource.Attributes attributes) {
					return getModelObject().getImage();
				}
			};
			return imageResource;
		}

		private Form<?> getForm(final Plant plant) {

			Form<?> form = new Form<Void>("form");// {

			return form;
		}

	}

	public abstract void werkPlantenLijstBij(final Plant plant);

	public Link<Void> backLink(final String name) {

		return new Link<Void>(name) {
			/**
			 * @see org.apache.wicket.markup.html.link.Link#onClick()
			 */
			@Override
			public void onClick() {
				setResponsePage(new PlantenPagina());
			}
		};
	}

}