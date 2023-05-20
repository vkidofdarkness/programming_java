package util;

import command.NoArgumentCommand.*;
import command.OneArgumentCommand.*;
import command.ScannerArgumentCommand.*;

import java.util.Scanner;

/**
 * класс инициатора шаблона - это команда, которая запускает методы класса collection
 */
public class Invoker {
    public Invoker(String filename) {
        this.fileName = filename;
    }

    String fileName;
    Command Remove_Head;
    Command Help;
    Command Info;
    Command Show;
    CommandWithScanner Add;
    CommandWithScanner Update;
    CommandWithScanner Add_If_Max;
    CommandWithScanner Add_If_Min;
    Command Remove_By_Id;
    Command Clear;
    Command Save;
    Command Exit;
    Command Sum_Of_Annual_Turnover;
    Command Average_Of_Annual_Turnover;
    Command Execute_Script;
    Command Filter_Starts_With_Name;

    public void setRemove_Head(Command remove_head) {Remove_Head = remove_head;}
    public void setAdd_If_Max(CommandWithScanner add_if_max) {Add_If_Max = add_if_max;}
    public void setAdd_If_Min(CommandWithScanner add_if_min) {Add_If_Min=add_if_min;}
    public void setHelp(Command help) {Help = help;}
    public void setInfo(Command info) {Info = info;}
    public void setShow(Command show) {Show = show;}
    public void setAdd(CommandWithScanner add) {Add = add;}
    public void setUpdate(CommandWithScanner update) {Update = update;}
    public void setRemove_By_Id(Command remove_By_Id) {Remove_By_Id = remove_By_Id;}
    public void setClear(Command clear) {Clear = clear;}
    public void setSave(Command save) {Save = save;}
    public void setExit(Command exit) {Exit = exit;}
    public void setSum_Of_Annual_Turnover(Command sum_Of_Annual_Turnover) {Sum_Of_Annual_Turnover = sum_Of_Annual_Turnover;}
    public void setAverage_Of_Annual_Turnover(Command average_Of_Annual_Turnover) {Average_Of_Annual_Turnover = average_Of_Annual_Turnover;}
    public void setExecute_Script(Command execute_Script) {Execute_Script = execute_Script;}
    public void setFilter_Starts_With_Name(OneArgumentCommand filter_Starts_With_Name) {Filter_Starts_With_Name = filter_Starts_With_Name;}

    public void help() {
        Help.execute();
    }
    public void info() {
        Info.execute();
    }
    public void show() {
        Show.execute();
    }
    public void add(Scanner in) {Add.execute(in);}
    public void update(int id, Scanner in) {
        Update.execute(id, in);
    }
    public void remove_by_id(int id) {
        Remove_By_Id.execute(id);
    }
    public void clear() {
        Clear.execute();
    }
    public void save() {Save.execute(fileName);}
    public void exit() { Exit.execute();}
    public void sum_of_annual_turnover() {
        Sum_Of_Annual_Turnover.execute();
    }
    public void average_of_annual_turnover() {Average_Of_Annual_Turnover.execute();}
    public void execute_script(String fileName) {Execute_Script.execute(fileName);}
    public void add_if_max(Scanner scanner) {Add_If_Max.execute(scanner);}
    public void add_if_min(Scanner scanner) {Add_If_Min.execute(scanner);}
    public void remove_head() {Remove_Head.execute();}
    public void filter_starts_with_name(String name) {Filter_Starts_With_Name.execute(name);}

    public void setCommands(Collection collection, Scanner scanner) {
        setHelp(new HelpCommand(collection));
        setInfo(new InfoCommand(collection));
        setShow(new ShowCommand(collection));
        setAdd(new AddCommand(collection, scanner));
        setUpdate(new UpdateCommand(collection, scanner));
        setRemove_By_Id(new Remove_By_IdCommand(collection));
        setClear(new ClearCommand(collection));
        setSave(new SaveCommand(collection));
        setExit(new ExitCommand(collection));
        setSum_Of_Annual_Turnover(new Sum_Of_Annual_TurnoverCommand(collection));
        setAverage_Of_Annual_Turnover((new Average_Of_Annual_TurnoverCommand(collection)));
        setExecute_Script(new Execute_ScriptCommand(collection));
        setRemove_Head(new Remove_HeadCommand(collection));
        setAdd_If_Max(new Add_If_MaxCommand(collection, scanner));
        setAdd_If_Min(new Add_If_MinCommand(collection, scanner));
        setFilter_Starts_With_Name (new Filter_Starts_With_NameCommand(collection));
    }
}
