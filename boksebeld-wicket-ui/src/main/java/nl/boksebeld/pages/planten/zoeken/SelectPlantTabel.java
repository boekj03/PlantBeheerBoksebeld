package nl.boksebeld.pages.planten.zoeken;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.request.resource.IResource;

import nl.boksebeld.domein.plant.Plant;

public class SelectPlantTabel extends PageableListView<PlantContainer> {

	private final Plant emptyPlantInstance = new Plant();

	public SelectPlantTabel(String id, List<PlantContainer> list) {

		super(id, list, 20);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1184299952876807147L;

	@Override
	protected void populateItem(ListItem<PlantContainer> plantContainerItem) {
		final PlantContainer plantContainer = plantContainerItem.getModelObject();

		populatePlantEen(plantContainerItem, plantContainer);
		populatePlantTwee(plantContainerItem, plantContainer);
		populatePlantDrie(plantContainerItem, plantContainer);
	}

	private void populatePlantEen(final ListItem<PlantContainer> plantContainerItem,
			final PlantContainer plantContainer) {

		final Plant plantEen = plantContainer.getPlantEen();
		plantContainerItem.add(new Label("planteen_nederlandseNaam", plantEen.getNederlandseNaam()));
		plantContainerItem.add(new Label("planteen_beschrijving", plantEen.getBeschrijving()));
		plantContainerItem.add(new Label("planteen_hoogte", plantEen.getHoogte()));
		Label labelBloeitijd = new Label("planteen_bloeitijd", plantEen.getBloeitijdWeergave());
		labelBloeitijd.setEscapeModelStrings(false);
		plantContainerItem.add(labelBloeitijd);
		plantContainerItem.add(new Label("planteen_grondsoort", plantEen.getGrondsoort()));

		CheckBox checkBox = new CheckBox("planteen_selected", new PropertyModel<Boolean>(plantEen, "selected"));
		plantContainerItem.add(checkBox);

		IResource imageResource = new DynamicImageResource() {
			@Override
			protected byte[] getImageData(IResource.Attributes attributes) {
				return plantEen.getImage();
			}
		};
		Image image = new Image("planteen_image", imageResource);
		plantContainerItem.add(image);
	}

	private void populatePlantTwee(ListItem<PlantContainer> plantContainerItem, final PlantContainer plantContainer) {
		Plant plantTwee = plantContainer.getPlantTwee();
		boolean visible = true;
		if (plantTwee == null) {
			plantTwee = emptyPlantInstance;
			visible = false; // een emptyinstance moet niet zichtbaar zijn
		}
		voegPlantTweeToe(plantContainerItem, plantTwee, visible);

	}

	private void voegPlantTweeToe(ListItem<PlantContainer> plantContainerItem, final Plant plantTwee, boolean visible) {
		Label labelnaam = new Label("planttwee_nederlandseNaam", plantTwee.getNederlandseNaam());
		Label labelBeschrijving = new Label("planttwee_beschrijving", plantTwee.getBeschrijving());
		Label hoogte = new Label("planttwee_hoogte", plantTwee.getHoogte());
		Label bloeitijd = new Label("planttwee_bloeitijd", plantTwee.getBloeitijdWeergave());
		bloeitijd.setEscapeModelStrings(false);
		Label grondsoort = new Label("planttwee_grondsoort", plantTwee.getGrondsoort());

		labelnaam.setVisible(visible);
		labelBeschrijving.setVisible(visible);
		hoogte.setVisible(visible);
		bloeitijd.setVisible(visible);
		grondsoort.setVisible(visible);

		plantContainerItem.add(labelnaam);
		plantContainerItem.add(labelBeschrijving);
		plantContainerItem.add(hoogte);
		plantContainerItem.add(bloeitijd);
		plantContainerItem.add(grondsoort);

		CheckBox checkBox = new CheckBox("planttwee_selected", new PropertyModel<Boolean>(plantTwee, "selected"));
		checkBox.setVisible(visible);
		plantContainerItem.add(checkBox);

		IResource imageResource = new DynamicImageResource() {
			@Override
			protected byte[] getImageData(IResource.Attributes attributes) {
				return plantTwee.getImage();
			}
		};

		Image image = new Image("planttwee_image", imageResource);
		image.setVisible(visible);
		plantContainerItem.add(image);
	}

	private void populatePlantDrie(ListItem<PlantContainer> plantContainerItem, final PlantContainer plantContainer) {
		Plant plantDrie = plantContainer.getPlantDrie();
		boolean visible = true;
		if (plantDrie == null) {
			plantDrie = emptyPlantInstance;
			visible = false; // een emptyinstance moet niet zichtbaar zijn
		}

		voegPlantDrieToe(plantContainerItem, plantDrie, visible);

	}

	private void voegPlantDrieToe(ListItem<PlantContainer> plantContainerItem, final Plant plantDrie, boolean visible) {
		Label labelnaam = new Label("plantdrie_nederlandseNaam", plantDrie.getNederlandseNaam());
		Label labelBeschrijving = new Label("plantdrie_beschrijving", plantDrie.getBeschrijving());
		Label hoogte = new Label("plantdrie_hoogte", plantDrie.getHoogte());
		Label bloeitijd = new Label("plantdrie_bloeitijd", plantDrie.getBloeitijdWeergave());
		bloeitijd.setEscapeModelStrings(false);
		Label grondsoort = new Label("plantdrie_grondsoort", plantDrie.getGrondsoort());

		labelnaam.setVisible(visible);
		labelBeschrijving.setVisible(visible);
		hoogte.setVisible(visible);
		bloeitijd.setVisible(visible);
		grondsoort.setVisible(visible);

		plantContainerItem.add(labelnaam);
		plantContainerItem.add(labelBeschrijving);
		plantContainerItem.add(hoogte);
		plantContainerItem.add(bloeitijd);
		plantContainerItem.add(grondsoort);

		CheckBox checkBox = new CheckBox("plantdrie_selected", new PropertyModel<Boolean>(plantDrie, "selected"));
		checkBox.setVisible(visible);
		plantContainerItem.add(checkBox);

		plantContainerItem.add(labelnaam);
		plantContainerItem.add(labelBeschrijving);
		IResource imageResource = new DynamicImageResource() {
			@Override
			protected byte[] getImageData(IResource.Attributes attributes) {
				return plantDrie.getImage();
			}
		};

		Image image = new Image("plantdrie_image", imageResource);

		image.setVisible(visible);
		plantContainerItem.add(image);
	}

}
