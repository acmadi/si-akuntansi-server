package com.devteam.accounting.persistence.app;

import com.devteam.accounting.persistence.type.MenuLocation;
import com.devteam.accounting.persistence.type.MenuType;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * User: pancara
 * Date: 1/11/14
 * Time: 12:05 PM
 */
@Entity
@Table(name = "MENU")
@Audited
@SequenceGenerator(name = "menu_seq", sequenceName = "menu_seq", allocationSize = 1)
public class Menu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_seq")
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "path")
    private String path;

    @Column(name = "caption")
    private String caption;

    @Column(name = "description")
    private String description;

    @Column(name = "icon")
    private String icon;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private MenuType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "location")
    private MenuLocation location;

    @ManyToOne
    @JoinColumn(name = "parent")
    private Menu parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    @OrderBy("index")
    private List<Menu> children = new ArrayList<>();

    @Column(name = "idx")
    private Integer index;

    public Menu() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    public MenuLocation getLocation() {
        return location;
    }

    public void setLocation(MenuLocation location) {
        this.location = location;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
