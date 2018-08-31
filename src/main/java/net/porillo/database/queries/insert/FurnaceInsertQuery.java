package net.porillo.database.queries.insert;

import net.porillo.database.api.InsertQuery;
import net.porillo.objects.Furnace;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FurnaceInsertQuery extends InsertQuery {

	private Furnace furnace;

	public FurnaceInsertQuery(Furnace furnace) {
		super("furnaces");
		this.furnace = furnace;
	}

	@Override
	public String getSQL() {
		return "INSERT INTO furnaces (uniqueID, ownerUUID, worldName, blockX, blockY, blockZ) VALUES (?,?,?,?,?,?)";
	}

	@Override
	public PreparedStatement prepareStatement(Connection connection) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(getSQL());
		preparedStatement.setString(1, furnace.getUniqueID().toString());
		preparedStatement.setString(2, furnace.getOwner().getUuid().toString());
		preparedStatement.setString(3, furnace.getLocation().getWorld().getName());
		preparedStatement.setInt(4, furnace.getLocation().getBlockX());
		preparedStatement.setInt(5, furnace.getLocation().getBlockY());
		preparedStatement.setInt(6, furnace.getLocation().getBlockZ());
		return preparedStatement;
	}

	@Override
	public Long getUniqueID() {
		return furnace.getUniqueID();
	}
}
