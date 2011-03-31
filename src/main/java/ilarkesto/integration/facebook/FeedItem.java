package ilarkesto.integration.facebook;

import ilarkesto.core.base.Str;
import ilarkesto.core.json.JsonObject;

import java.util.Date;

public class FeedItem extends Item {

	public FeedItem(JsonObject data) {
		super(data);
	}

	public String getBestImage() {
		String picture = getPicture();
		if (!Str.isBlank(picture)) return picture;
		return getIcon();
	}

	public String getBestText() {
		StringBuilder sb = new StringBuilder();

		String message = getMessage();
		String caption = getCaption();
		String name = getName();
		String description = getDescription();
		String link = getBestLink();

		if (!Str.isBlank(message)) {
			sb.append(message);
		}

		if (!Str.isBlank(caption) || !Str.isBlank(name) || !Str.isBlank(description) || !Str.isBlank(link)) {
			if (!Str.isBlank(message)) sb.append("\n\n ");
			sb.append("[ ");

			if (!Str.isBlank(caption)) {
				sb.append(caption).append(": ");
			}

			if (!Str.isBlank(name)) {
				sb.append(name);
			}

			if (!Str.isBlank(description)) {
				if (!Str.isBlank(name)) sb.append(" | ");
				sb.append(description);
			}

			if (!Str.isBlank(link)) {
				sb.append(" => ").append(link);
			}

			sb.append(" ]");
		}

		return sb.toString();
	}

	public String getBestLink() {
		return getLink();
	}

	public final String getCaption() {
		return data.getString("caption");
	}

	public final String getName() {
		return data.getString("name");
	}

	public final String getDescription() {
		return data.getString("description");
	}

	public final String getIcon() {
		return data.getString("icon");
	}

	public final String getLink() {
		return data.getString("link");
	}

	public final String getPicture() {
		return data.getString("picture");
	}

	public final String getMessage() {
		return data.getString("message");
	}

	public final Person getFrom() {
		if (!data.contains("from")) return null;
		return new Person(data.getObject("from"));
	}

	public final Date getCreatedTime() {
		return data.getDate("created_time", DATE_FORMAT);
	}

	public final Date getUpdatedTime() {
		return data.getDate("updated_time", DATE_FORMAT);
	}

	public final String getObjectId() {
		return data.getString("object_id");
	}

	public final String getType() {
		return data.getString("type");
	}

}
