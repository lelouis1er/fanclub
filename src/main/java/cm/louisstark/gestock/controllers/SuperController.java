package cm.louisstark.gestock.controllers;


import cm.louisstark.gestock.entities.FanClub;
import cm.louisstark.gestock.entities.Membre;
import cm.louisstark.gestock.entities.Menu;
import cm.louisstark.gestock.entities.ModelOperation;
import cm.louisstark.gestock.entities.Mouchard;
import cm.louisstark.gestock.entities.Operation;
import cm.louisstark.gestock.entities.PrivilegesUtilisateur;
import cm.louisstark.gestock.entities.RestrictionPrivilege;
import cm.louisstark.gestock.entities.RolePrivilege;
import cm.louisstark.gestock.entities.RoleUtilisateur;
import cm.louisstark.gestock.entities.SessionOp;
import cm.louisstark.gestock.entities.TypeMembre;

import cm.louisstark.gestock.entities.TypeOperation;

import cm.louisstark.gestock.entities.Utilisateur;
import cm.louisstark.gestock.sessions.FanClubFacadeLocal;
import cm.louisstark.gestock.sessions.MembreFacadeLocal;

import cm.louisstark.gestock.sessions.MenuFacadeLocal;
import cm.louisstark.gestock.sessions.ModelOperationFacadeLocal;
import cm.louisstark.gestock.sessions.MouchardFacadeLocal;
import cm.louisstark.gestock.sessions.OperationFacadeLocal;

import cm.louisstark.gestock.sessions.PrivilegesutilisateurFacadeLocal;

import cm.louisstark.gestock.sessions.RestrictionprivilegeFacadeLocal;

import cm.louisstark.gestock.sessions.RoleprivilegeFacadeLocal;
import cm.louisstark.gestock.sessions.RoleutilisateurFacadeLocal;
import cm.louisstark.gestock.sessions.SessionOpFacadeLocal;
import cm.louisstark.gestock.sessions.TypeMembreFacadeLocal;

import cm.louisstark.gestock.sessions.TypeOperationFacadeLocal;

import cm.louisstark.gestock.sessions.UtilisateurFacadeLocal;

import cm.louisstark.gestock.utilitaires.Printer;
import cm.louisstark.gestock.utilitaires.SessionManagerImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.transaction.UserTransaction;

/**
 *
 * @author Louis Stark
 */
public abstract class SuperController {
    
    protected Properties app_properties;
    
    protected String mode = "";
    
    protected Printer printer = new Printer();
    
    protected final String src_path = "/WEB-INF/reports/";
    protected final String sub_path = "/WEB-INF/reports/sub/";
    protected String SUBREPORT_PATH;
    protected String jasper_path;
    protected Map param = new HashMap<>();
    protected String file_name = "";
    
    protected boolean creer = false, modifier = false, details = false, supprimer = false;
    
    
    @ManagedProperty (value = "#{manageBean}")
    protected SessionManagerImpl sessionManager;
    
    @Resource
    protected UserTransaction ut;
    
    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;
    protected List<Mouchard> list_mouchards = new ArrayList<>();
    protected Mouchard mouchard = new Mouchard(0);
    
       
    @EJB
    protected MenuFacadeLocal menuFacadeLocal;
    protected List<Menu> list_menus = new ArrayList<>();
    protected Menu menu = new Menu(0);
    
    
    @EJB
    protected PrivilegesutilisateurFacadeLocal privilegesutilisateurFacadeLocal;
    protected List<PrivilegesUtilisateur> list_privilegesutilisateurs = new ArrayList<>();
    protected PrivilegesUtilisateur privilegesutilisateur = new PrivilegesUtilisateur(0);

    
    @EJB
    protected RestrictionprivilegeFacadeLocal restrictionprivilegeFacadeLocal;
    protected List<RestrictionPrivilege> list_restrictionprivileges = new ArrayList<>();
    protected RestrictionPrivilege restrictionprivilege = new RestrictionPrivilege(0);
    
    @EJB
    protected RoleprivilegeFacadeLocal roleprivilegeFacadeLocal;
    protected List<RolePrivilege> list_roleprivileges = new ArrayList<>();
    protected RolePrivilege roleprivilege = new RolePrivilege(0);
    
    @EJB
    protected RoleutilisateurFacadeLocal roleutilisateurFacadeLocal;
    protected List<RoleUtilisateur> list_roleutilisateurs = new ArrayList<>();
    protected RoleUtilisateur roleutilisateur = new RoleUtilisateur(0);
    
    @EJB
    protected UtilisateurFacadeLocal utilisateurFacadeLocal;
    protected List<Utilisateur> list_utilisateurs = new ArrayList<>();
    protected Utilisateur utilisateur = new Utilisateur(0);
    
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @EJB
    protected SessionOpFacadeLocal sessionFacadeLocal;
    protected List<SessionOp> list_sessions = new ArrayList<>();
    protected SessionOp session = new SessionOp(0);
    
    @EJB
    protected TypeOperationFacadeLocal typeOperationFacadeLocal;
    protected List<TypeOperation> list_typeOperations = new ArrayList<>();
    protected TypeOperation typeOperation = new TypeOperation(0);
    
    @EJB
    protected FanClubFacadeLocal fanClubFacadeLocal;
    protected List<FanClub> list_fanClub = new ArrayList<>();
    protected FanClub fanClub = new FanClub();
    
    
    @EJB
    protected MembreFacadeLocal membreFacadeLocal;
    protected List<Membre> list_membres = new ArrayList<>();
    protected Membre membre = new Membre(0);
    
    @EJB
    protected TypeMembreFacadeLocal typeMembreFacadeLocal;
    protected List<TypeMembre> list_typeMembres = new ArrayList<>();
    protected TypeMembre typeMembre = new TypeMembre(0);
    
    @EJB
    protected ModelOperationFacadeLocal modelOperationFacadeLocal;
    protected List<ModelOperation> list_modelOperation = new ArrayList<>();
    protected ModelOperation modelOperation = new ModelOperation(0);
    
    
    @EJB
    protected OperationFacadeLocal operationFacadeLocal;
    protected List<Operation> list_operations = new ArrayList<>();
    protected Operation operation = new Operation(0l);
    
   
    ///////////////////
    /*      Fonctions usuelles           */
    //////////////////////////////////////////  
    
    public abstract void define_create_update_delete_details (Object o);
    
    public void define_list_menus () {}
    public void define_list_privilegesutilisateurs () {}
    public void define_list_restrictionsprivileges () {}
    public void define_list_roleprivileges () {}
    public void define_list_roleutilisateur () {}
    public void define_list_utilisateurs () {}
    public void define_list_mouchards () {}
    /////////////////////////////////////////////////////////////////////////////
   
    public void define_list_sessions () {}
    public void define_list_typeOperations () {}
    public void define_list_operations() {}
    public void define_list_modelOperations() {}
    public void define_list_membres() {}
    public void define_list_fanClubs() {}
    public void define_list_typeMembre() {}

    

    // ----------------------------------------------------------------------------------------
    /*      getters and setters            */
    // ------------------------------------------------------------------------------------
    /////////   getters des listes
    public Properties getApp_properties() {
        return app_properties;
    }


    public List<Menu> getList_menus() {
        define_list_menus();
        return list_menus;
    }
   

    public List<PrivilegesUtilisateur> getList_privilegesUtilisateurs() {
        define_list_privilegesutilisateurs();
        return list_privilegesutilisateurs;
    }

    public List<RestrictionPrivilege> getList_restrictionprivileges() {
        define_list_restrictionsprivileges();
        return list_restrictionprivileges;
    }

    public List<RolePrivilege> getList_roleprivileges() {
        define_list_roleprivileges();
        return list_roleprivileges;
    }

    public List<RoleUtilisateur> getList_roleutilisateurs() {
        define_list_roleutilisateur();
        return list_roleutilisateurs;
    }

    public List<Utilisateur> getList_utilisateurs() {
        define_list_utilisateurs();
        return list_utilisateurs;
    }

    public List<Mouchard> getList_mouchards() {
        define_list_mouchards();
        return list_mouchards;
    }
    
//////////////////////////////

    
    public List<SessionOp> getList_sessions() {
        define_list_sessions();
        return list_sessions;
    }

    public List<TypeOperation> getList_typeOperations() {
        define_list_typeOperations();
        return list_typeOperations;
    }

    public List<FanClub> getList_fanClub() {
        define_list_fanClubs();
        return list_fanClub;
    }

    public List<Membre> getList_membres() {
        define_list_membres();
        return list_membres;
    }

    public List<TypeMembre> getList_typeMembres() {
        define_list_typeMembre();
        return list_typeMembres;
    }

    public List<ModelOperation> getList_modelOperation() {
        define_list_modelOperations();
        return list_modelOperation;
    }

    public List<Operation> getList_operations() {
        define_list_operations();
        return list_operations;
    }
    ///////////////////////////////// Setter et getters -------------------------------------
    
    
    public FanClub getFanClub() {
        return fanClub;
    }

    public void setFanClub(FanClub fanClub) {    
        this.fanClub = fanClub;
        define_create_update_delete_details(fanClub);
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
        define_create_update_delete_details(menu);
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
        define_create_update_delete_details(membre);
    }

    public TypeMembre getTypeMembre() {
        return typeMembre;
    }

    public void setTypeMembre(TypeMembre typeMembre) {
        this.typeMembre = typeMembre;
        define_create_update_delete_details(typeMembre);
    }

    public ModelOperation getModelOperation() {
        return modelOperation;
    }

    public void setModelOperation(ModelOperation modelOperation) {
        this.modelOperation = modelOperation;
        define_create_update_delete_details(modelOperation);
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
        define_create_update_delete_details(operation);
    }


    public PrivilegesUtilisateur getPrivilegesutilisateur() {
        return privilegesutilisateur;
    }

    public void setPrivilegesutilisateur(PrivilegesUtilisateur privilegesutilisateur) {
        this.privilegesutilisateur = privilegesutilisateur;
        define_create_update_delete_details(privilegesutilisateur);
    }

    public RestrictionPrivilege getRestrictionprivilege() {
        return restrictionprivilege;
    }

    public void setRestrictionprivilege(RestrictionPrivilege restrictionprivilege) {
        this.restrictionprivilege = restrictionprivilege;
        define_create_update_delete_details(restrictionprivilege);
    }

    public RolePrivilege getRoleprivilege() {
        return roleprivilege;
    }

    public void setRoleprivilege(RolePrivilege roleprivilege) {
        this.roleprivilege = roleprivilege;
        define_create_update_delete_details(roleprivilege);
    }

    public RoleUtilisateur getRoleutilisateur() {
        return roleutilisateur;
    }

    public void setRoleutilisateur(RoleUtilisateur roleutilisateur) {
        this.roleutilisateur = roleutilisateur;
        define_create_update_delete_details(roleutilisateur);
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        define_create_update_delete_details(utilisateur);
    }

    public void setSessionManager(SessionManagerImpl sessionManager) {
        this.sessionManager = sessionManager;
    }

    public Mouchard getMouchard() {
        return mouchard;
    }

    public void setMouchard(Mouchard mouchard) {
        this.mouchard = mouchard;
        define_create_update_delete_details(mouchard);
    }

    public boolean isCreer() {
        return creer;
    }

    public boolean isModifier() {
        return modifier;
    }

    public boolean isDetails() {
        return details;
    }

    public boolean isSupprimer() {
        return supprimer;
    }

    public String getMode() {
        return mode;
    }
    //////////////////////////////////////////////////////////////////


    public SessionOp getSession() {
        return session;
    }

    public void setSession(SessionOp session) {
        this.session = session;
        define_create_update_delete_details(session);
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
        define_create_update_delete_details(typeOperation);
    }

    
    
}
