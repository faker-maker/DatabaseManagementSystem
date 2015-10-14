package ua.kateros.sybd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kateros.sybd.entities.Database;
import ua.kateros.sybd.entities.Root;
import ua.kateros.sybd.entities.Table;

import java.util.List;

@Controller
@RequestMapping("/databases")
public class DatabasesController {
    @Autowired
    private Root root;

    @RequestMapping("/all")
    public String viewAllDatabases(Model model) {
        List<Database> databases = root.getDatabases();
        model.addAttribute("databases", databases);
        return "viewdatabases";
    }

    @RequestMapping("/add")
    public String addDatabase(@RequestParam(required = true, value = "name") String name) {
        Database database = new Database(name);
        root.addDatabase(database);
        return "redirect:/databases/all";
    }

    @RequestMapping("/view")
    public String viewDatabase(@RequestParam(required = true, value = "name") String name, Model model) {
        Database database = root.getDatabaseWithName(name);
        model.addAttribute("database", database);
        return "viewdb";
    }

    @RequestMapping("/{dbname}/addtable")
    public String addTable(@RequestParam(required = true, value = "tablename") String tableName,
                           @PathVariable(value = "dbname") String dbName, Model model) {
        Database database = root.getDatabaseWithName(dbName);
        Table table = new Table(tableName);
        database.addTable(table);
        model.addAttribute("database", database);
        return "redirect:/databases/view?name=" + dbName;
    }

    @RequestMapping("/{dbname}/view")
    public String viewTable(@RequestParam(required = true, value = "table") String tableName,
                           @PathVariable(value = "dbname") String dbName, Model model) {
        Database database = root.getDatabaseWithName(dbName);
        Table table = database.getTableWithName(tableName);
        model.addAttribute("table", table);
        return "viewtable";
    }
}
