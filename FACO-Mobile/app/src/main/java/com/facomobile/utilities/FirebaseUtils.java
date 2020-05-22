package com.facomobile.utilities;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseUtils {

    /*public static DatabaseReference getBaseDatabaseRef() {
        return FirebaseDatabase.getInstance().getReference();
    }

    public static DatabaseReference getBaseMainRef() {
        return getBaseDatabaseRef().child(FAMSConstants.COLUMN_MAIN);
    }



    *//*----------------------
    -------- INITS ---------
    ----------------------*//*

    public static DatabaseReference getBaseInitRef() {
        return getBaseDatabaseRef().child(FAMSConstants.COLUMN_INIT);
    }

    public static DatabaseReference getBaseInitResidentRef() {
        return getBaseInitRef().child(FAMSConstants.COLUMN_RESIDENTS);
    }

    public static DatabaseReference getInitResidentRef(String phoneNumber) {
        return getBaseInitResidentRef()
                .child(phoneNumber);
    }

    public static DatabaseReference getInitResidentStatusRef(String phoneNumber) {
        return getInitResidentRef(phoneNumber).child(FAMSConstants.COLUMN_INIT_STATUS);
    }

    public static DatabaseReference getInitResidentFirstNameRef(String residentPhoneNumber) {
        return getInitResidentRef(residentPhoneNumber).child(FAMSConstants.COLUMN_FIRST_NAME);
    }

    // END OF INITS



    *//*------------------------
    --------- RESIDENTS ----------
    ------------------------*//*

    public static DatabaseReference getBaseResidentsRef() {
        return getBaseMainRef().child(FAMSConstants.COLUMN_RESIDENTS);
    }

    public static DatabaseReference getResidentRef(String uid) {
        return getBaseResidentsRef()
                .child(uid);
    }

    public static DatabaseReference getResidentFirstNameRef(String uid) {
        return getResidentRef(uid).child(FAMSConstants.COLUMN_FIRST_NAME);
    }

    // END OF RESIDENTS




    *//*--------------------------
    ------- RELATIVES ----------
    --------------------------*//*

    public static DatabaseReference getResidentRelativesRef(String residentId) {
        return getResidentRef(residentId).child(FAMSConstants.COLUMN_RELATIVES);
    }

    public static DatabaseReference getResidentRelativeRef(String residentId, String relativePhoneNumber) {
        return getResidentRelativesRef(residentId).child(relativePhoneNumber);
    }

    // END OF RESIDENTS

    *//*------------------------
    ----- NOTIFICATIONS ------
    ------------------------*//*

    public static DatabaseReference getBaseNotificationRef() {
        return getBaseMainRef()
                .child(FAMSConstants.COLUMN_NOTIFICATION);
    }

    // END OF MAIL



    *//*-----------------------
    --------- VISITORS ------
    -----------------------*//*

    public static DatabaseReference getBaseVisitorsRef() {
        return getBaseMainRef().child(FAMSConstants.COLUMN_VISITORS);
    }

    public static DatabaseReference getVisitorsRef(String uid) {
        return getBaseVisitorsRef()
                .child(uid);
    }

    public static DatabaseReference getVisitorRef(String uid, String id) {
        return getVisitorsRef(uid)
                .child(id);
    }

    public static DatabaseReference getVisitorCheckInTimeRef(String residentId, String id) {
        return getVisitorRef(residentId, id).child(FAMSConstants.COLUMN_CHECK_IN_TIME);
    }

    public static DatabaseReference getVisitorCheckOutTimeRef(String residentId, String id) {
        return getVisitorRef(residentId, id).child(FAMSConstants.COLUMN_CHECK_OUT_TIME);

    }

    // END OF VISITORS



    *//*----------------------
    -------- FEEDBACK ------
    ----------------------*//*

    public static DatabaseReference getBaseFeedbackRef() {
        return getBaseMainRef().child(FAMSConstants.COLUMN_FEEDBACK);
    }

    public static DatabaseReference getUserFeedbackRef(String uid) {
        return getBaseFeedbackRef().child(uid);
    }

    public static DatabaseReference getFeedbackConversationRef(String uid, String conversationId) {
        return getUserFeedbackRef(uid).child(conversationId);
    }

    public static DatabaseReference getFeedbackRef(String uid, String conversationId, String feedbackId) {
        return getFeedbackConversationRef(uid, conversationId).child(feedbackId);
    }

    // END OF FEEDBACK



    *//*-----------------------
    ---- CLOUD MESSAGING ----
    -----------------------*//*

    private static DatabaseReference getBaseCloudMessagingTokensRef() {
        return getBaseDatabaseRef()
                .child(FAMSConstants.COLUMN_MESSAGING_TOKENS);
    }

    public static DatabaseReference getCloudMessagingTokenRef(String uid) {
        return getBaseCloudMessagingTokensRef().child(uid);
    }

    // END OF CLOUD OF MESSAGING

    *//*-------------------------
    ------------ ADMIN --------
    -------------------------*//*
    private static DatabaseReference getBaseAdminRef() {
        return getBaseMainRef().child(FAMSConstants.COLUMN_ADMIN);
    }

    private static DatabaseReference getBaseAdminAccessRef() {
        return getBaseAdminRef().child(FAMSConstants.COLUMN_ACCESS);
    }

    public static DatabaseReference getBaseAdminsRef() {
        return getBaseAdminRef().child(FAMSConstants.COLUMN_ADMINS);
    }

    public static DatabaseReference getAdminAccessRef(String accessCode) {
        return getBaseAdminAccessRef().child(accessCode);
    }

    public static DatabaseReference getAdminRef(String id) {
        return getBaseAdminsRef().child(id);
    }

    // END OF ADMIN




    *//*----------------------
    ------- CONTROL --------
    ----------------------*//*

    public static DatabaseReference getBaseControlRef() {
        return getBaseMainRef().child(FAMSConstants.COLUMN_CONTROL);
    }

    public static DatabaseReference getBaseInviteCodesRef() {
        return getBaseControlRef().child(FAMSConstants.COLUMN_INVITE_CODES);
    }

    public static DatabaseReference getInviteCodeRef(String inviteCode) {
        return getBaseInviteCodesRef()
                .child(inviteCode);
    }

    public static DatabaseReference getInviteCodeStatusRef(String inviteCode) {
        return getInviteCodeRef(inviteCode).child(FAMSConstants.COLUMN_STATUS);
    }

    public static DatabaseReference getBaseGuardsRef() {
        return getBaseControlRef().child(FAMSConstants.COLUMN_GUARDS);
    }

    public static DatabaseReference getGuardRef(String phoneNumber) {
        return getBaseGuardsRef().child(phoneNumber);
    }

    private static DatabaseReference getBaseControlActivityRef() {
        return getBaseControlRef().child(FAMSConstants.COLUMN_ACTIVITY);
    }

    public static DatabaseReference getBaseControlCheckInActivityRef() {
        return getBaseControlActivityRef()
                .child(FAMSConstants.COLUMN_CHECK_INS);
    }

    public static DatabaseReference getBaseControlCheckOutActivityRef() {
        return getBaseControlActivityRef()
                .child(FAMSConstants.COLUMN_CHECK_OUTS);
    }

    public static DatabaseReference getBaseAllControlCheckInActivityRef() {
        return getBaseControlCheckInActivityRef().child(FAMSConstants.COLUMN_ALL);
    }

    public static DatabaseReference getBaseAllControlCheckOutActivityRef() {
        return getBaseControlCheckOutActivityRef().child(FAMSConstants.COLUMN_ALL);
    }

    public static DatabaseReference getCheckInActivityRef(String securityUid) {
        return getBaseControlCheckInActivityRef()
                .child(securityUid);
    }

    public static DatabaseReference getCheckOutActivityRef(String securityUid) {
        return getBaseControlCheckOutActivityRef()
                .child(securityUid);
    }

    public static DatabaseReference getCheckInActivityVisitorRef(String securityUid, String visitorUid) {
        return getCheckInActivityRef(securityUid).child(visitorUid);
    }

    public static DatabaseReference getCheckOutActivityVisitorRef(String securityUid, String visitorUid) {
        return getCheckOutActivityRef(securityUid).child(visitorUid);
    }

    public static DatabaseReference getAllCheckInActivityVisitorRef(String visitorUid) {
        return getBaseAllControlCheckInActivityRef().child(visitorUid);
    }

    public static DatabaseReference getAllCheckOutActivityVisitorRef(String visitorUid) {
        return getBaseAllControlCheckOutActivityRef().child(visitorUid);
    }

    // END OF CONTROL



    *//*----------------------
    ------ PRESENCE --------
    ----------------------*//*

    private static DatabaseReference getBasePresenceRef() {
        return getBaseMainRef().child(FAMSConstants.COLUMN_PRESENCE);
    }

    private static DatabaseReference getBaseControlPresenceRef() {
        return getBasePresenceRef().child(FAMSConstants.COLUMN_CONTROL);
    }

    public static DatabaseReference getPresenceRef(@NonNull String uid) {
        return getBaseControlPresenceRef().child(uid);
    }

    // END OF PRESENCE
*/

    /*-------------------------
            ---------- STORAGE --------
            -------------------------*/

    public static StorageReference getBaseStorageRef() {
        return FirebaseStorage.getInstance().getReference();
    }

    public static StorageReference getUserStorageRef(String uid) {
        return getBaseStorageRef().child(uid);
    }

    // END OF STORAGE





    /*------------------------
    -------- UTILITIES -------
    ------------------------*/

    public static String makeRef(String... refPaths) {
        StringBuilder stringBuilder = new StringBuilder(1024);
        stringBuilder.append(refPaths[0]);
        for (int i = 1; i < refPaths.length; i++) {
            stringBuilder.append("/").append(refPaths[i]);
        }
        return stringBuilder.toString();
    }

    // END OF UTILITIES
}
