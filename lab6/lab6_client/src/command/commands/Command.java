package command.commands;

import command.Collection;
import classes.Organization;

import java.io.Serializable;

public abstract class Command implements Serializable {
    private Argument argument;
    private Organization organization;

    public MessageCommand execute(Collection collection) { return new MessageCommand();}
    
    public void setArgument(Argument argument) { this.argument = argument;}

    public Argument getArgument() {return argument;}

    public void setOrganization(Organization organization) {this.organization = organization;}

    public Organization getOrganization() {return organization;}
}
