package de.bentigorlich.batrikaserver.Entities;

public enum MessageType
{
	message(0),
	room_message(1),
	group_message(2),
	message_received(3),
	message_received_user(4),
	message_fail(5),
	message_seen(6),

	login(7),
	login_fail(8),
	selfinfo(9),

	room_join(10),
	room_join_fail(11),

	group_join(12),
	group_join_fail(13),

	user_block(14),
	user_block_fail(15),
	user_unblock(16),
	user_unblock_fail(17),
	user_search(18),
	user_info(19),
	user_create(20),
	user_create_fail(21),
	user_change(22),
	user_change_fail(23),
	user_delete(24),
	user_delete_fail(25),

	room_info(26),
	room_search(27),
	room_create(28),
	room_create_fail(29),
	room_change(30),
	room_change_fail(31),
	room_delete(32),
	room_delete_fail(33),

	group_create(34),
	group_create_fail(35),
	group_change(36),
	group_change_fail(37),
	group_delete(38),
	group_delete_fail(39),

	contact_add(40),
	contact_add_fail(41),
	contact_remove(42),
	contact_remove_fail(43),

	media(44),
	profile_picture(45),;
	int num;

	MessageType(int num)
	{
		this.num = num;
	}

	public int toInt()
	{
		return num;
	}

	public static MessageType get(int i)
	{
		switch(i)
		{
			case 0:
				return message;
			case 1:
				return room_message;
			case 2:
				return group_message;
			case 3:
				return message_received;
			case 4:
				return message_received_user;
			case 5:
				return message_fail;
			case 6:
				return message_seen;
			case 7:
				return login;
			case 8:
				return login_fail;
			case 9:
				return selfinfo;
			case 10:
				return room_join;
			case 11:
				return room_join_fail;
			case 12:
				return group_join;
			case 13:
				return group_join_fail;
			case 14:
				return user_block;
			case 15:
				return user_block_fail;
			case 16:
				return user_unblock;
			case 17:
				return user_unblock_fail;
			case 18:
				return user_search;
			case 19:
				return user_info;
			case 20:
				return user_create;
			case 21:
				return user_create_fail;
			case 22:
				return user_change;
			case 23:
				return user_change_fail;
			case 24:
				return user_delete;
			case 25:
				return user_delete_fail;
			case 26:
				return room_info;
			case 27:
				return room_search;
			case 28:
				return room_create;
			case 29:
				return room_create_fail;
			case 30:
				return room_change;
			case 31:
				return room_change_fail;
			case 32:
				return room_delete;
			case 33:
				return room_delete_fail;
			case 34:
				return group_create;
			case 35:
				return group_create_fail;
			case 36:
				return group_change;
			case 37:
				return group_change_fail;
			case 38:
				return group_delete;
			case 39:
				return group_delete_fail;
			case 40:
				return contact_add;
			case 41:
				return contact_add_fail;
			case 42:
				return contact_remove;
			case 43:
				return contact_remove_fail;
			case 44:
				return media;
			case 45:
				return profile_picture;
			default:
				return message;
		}
	}
}
