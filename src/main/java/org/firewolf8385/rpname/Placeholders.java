package orgfirewolf8385.rpname;


import me.clip.placeholderapi.expansion.PlaceholderExpansion;

import org.bukkit.entity.Player;
import org.firewolf.rpname.RPName;

/**
 * This class will be registered through the register-method in the
 * plugins onEnable-method.
 */
class Placeholders extends PlaceholderExpansion
{

    /**
     * Since we register the expansion inside our own plugin, we
     * can simply use this method here to get an instance of our
     * plugin.
     *
     * @param plugin
     *        The instance of our plugin.
     */
    public Placeholders(RPName plugin){
        this.plugin = plugin;
    }

    /**
     * Because this is an internal class,
     * you must override this method to let PlaceholderAPI know to not unregister your expansion class when
     * PlaceholderAPI is reloaded
     *
     * @return true to persist through reloads
     */
    @Override
    public boolean persist(){
        return true;
    }

    /**
     * Because this is a internal class, this check is not needed
     * and we can simply return {@code true}
     *
     * @return Always true since it's an internal class.
     */
    @Override
    public boolean canRegister(){
        return true;
    }

    /**
     * The name of the person who created this expansion should go here.
     * <br>For convienience do we return the author from the plugin.yml
     *
     * @return The name of the author as a String.
     */
    @Override
    public String getAuthor(){
        return plugin.getDescription().getAuthors().toString();
    }

    /**
     * The placeholder identifier should go here.
     * <br>This is what tells PlaceholderAPI to call our onRequest
     * method to obtain a value if a placeholder starts with our
     * identifier.
     * <br>This must be unique and can not contain % or _
     *
     * @return The identifier in {@code %<identifier>_<value>%} as String.
     */
    @Override
    public String getIdentifier(){
        return "ep";
    }

    /**
     * This is the version of the expansion.
     * <br>You don't have to use numbers, since it is set as a String.
     *
     * For convienience do we return the version from the plugin.yml
     *
     * @return The version as a String.
     */
    @Override
    public String getVersion(){
        return plugin.getDescription().getVersion();
    }


    @Override
    public String onPlaceholderRequest(Player p, String identifier)
    {

        if(p == null)
        {
            return "";
        }

        ElytraPlayer player = ElytraPlayer.players.get(p.getUniqueId());

        // Coins
        if(identifier.equals("coins"))
        {
            return String.valueOf(player.getCoins());
        }

        // Kills
        if(identifier.equals("kills"))
        {
            return String.valueOf(player.getKills());
        }

        // Deaths
        if(identifier.equals("deaths"))
        {
            return String.valueOf(player.getDeaths());
        }

        // Killstreak
        if(identifier.equals("killstreak"))
        {
            return String.valueOf(player.getKillStreak());
        }

        // Kit
        if(identifier.equals("kit"))
        {

            switch(player.getKit())
            {
                case -1:
                    return "Spectator";
                case 1:
                    return "Sniper";
                case 2:
                    return "Knight";
                case 3:
                    return "Pyro";
                case 4:
                    return "Tank";
                case 5:
                    return "Chemist";
                case 6:
                    return "Stickman";
                case 7:
                    return "Bomber";
                case 8:
                    return "Healer";
                case 9:
                    return "Teleporter";
                case 10:
                    return "Spectral";
                case 11:
                    return "Puncher";
                default:
                    return "None";
            }

        }

        // Enabled
        if(identifier.equals("enabled"))
        {
            if(settings.getConfig().getBoolean("Enabled"))
            {
                return "&atrue";
            }
            else
            {
                return "&cfalse";
            }
        }

        // Get tag
        if(identifier.equals("tag"))
        {

            if(player.getTag() == 0)
            {
                return "";
            }
            else
            {
                for(Tag t : Tag.list.values())
                {
                    if(t.getNumber() == player.getTag())
                    {
                        return t.getTag() + " ";
                    }
                }
            }
        }

        return null;
    }
}