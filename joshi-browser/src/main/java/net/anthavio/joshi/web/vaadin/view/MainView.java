package net.anthavio.joshi.web.vaadin.view;

import javax.annotation.PostConstruct;

import net.anthavio.joshi.web.SessionData;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * @author xpoft
 */
@Component
@Scope("prototype")
@VaadinView(value = MainView.NAME, cached = true)
public class MainView extends Panel implements View {

	private static final long serialVersionUID = 1L;

	public static final String NAME = "";

	public static final ExternalResource link = new ExternalResource("#!" + NAME);

	@Autowired
	private SessionData sessionData;

	VerticalLayout layoutResults = new VerticalLayout();

	/*
		public static void bound(final TextField textField, final Button button, final int min) {

			textField.addTextChangeListener(new TextChangeListener() {

				@Override
				public void textChange(TextChangeEvent event) {
					if (event.getText() != null && event.getText().length() >= min) {
						button.setEnabled(true);
					} else if (button.isEnabled()) {
						button.setEnabled(false);
					}
				}
			});

			if (textField.getValue() != null && textField.getValue().length() >= min) {
				button.setEnabled(true);
			} else {
				button.setEnabled(false);
			}
			
			button.addClickListener(new ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					String value = textField.getValue();
					if (!StringUtils.isEmpty(value)) {
						searchAccount(value);
					}

				}
			});
		}
	*/
	@PostConstruct
	public void init() {
		setSizeFull();

		//layout.addComponent(new Link("Go to the UI scoped View", new ExternalResource("#!" + UIScopedView.NAME)));
		//setContent(layout);
	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
		String parameters = event.getParameters();
		if (!StringUtils.isBlank(parameters)) {

			String[] split = parameters.split("/");
			if (split.length == 2) {
				//
			} else if (split.length == 0) {
				//nothing to do...
			} else {
				Notification.show("Bugger! '" + parameters + "' ");
			}
		}
	}

}