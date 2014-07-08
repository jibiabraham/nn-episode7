package com.nn.studio.episode7;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by jibi on 8/7/14.
 */
public final class TestData {
    private TestData(){}

    public static void insertTestData(SQLiteDatabase db){
        Long now = System.currentTimeMillis();
        final String[] FORUMS = { "CAT", "XAT,SNAP,CMAT", "Bank PO", "GMAT", "GRE,GATE", "Jobs & Careers", "Lounge" };

        final String[] CAT_DISCUSSIONS = { "Official Quant Thread for CAT 2014!!", "SJMSOM, IIT Bombay - Admissions [2014-16]", "[Official] LR-DI Thread for CAT 2014", "[2012-2013] CAT Coaching Classes / Test Series Queries", "Sentence Correction, Fill in the blanks, Vocabulary" };
        final String[] XAT_DISCUSSIONS = { "Discussion on MBA CET 2014", "Target JBIMS 2014...", "[2012-2013] Good MAT Business Schools", "Join IILM GSM and give wings to your career", "AICTE approved 15 Months Exec - PGDM @ IILM Graduate School of Management" };
        final String[] BANKPO_DISCUSSIONS = { "ICICI Bank Probationary Officers Recruitment - May 2014 and August 2014 Batch", "SBI clerk exam preparation 2014-2015", "ibps po3 , clerk 3 and rrb discussion", "SBI PO 2014 Preparation & Discussion Thread", "IBPS PO3: Canara bank alloted candidates" };
        final String[] GMAT_DISCUSSIONS = { "International educational options beyond the MBA", "[Official] Tuck School of Business - Dartmouth Admission Query Thread", "ISB Class of 2016 Aspirants", "IIMA PGPX (1 Yr Full Time MBA) Class of 2016 aspirants (PGPX 2015-16)", "Singapore Management University (SMU)" };
        final String[] GRE_DISCUSSIONS = { "TARGET BEL PE 2014", "ONGC SET D ANSWER KEY", "IES(engineering services) exam 2015 mechanical", "Buy Quality Real And Fake Passports,Driver's License,ID Cards(((nikolawil@gmail.com)))", "target GATE 2015 mechanical " };
        final String[] JOBS_DISCUSSIONS = { "GRSE asst. manager E-1 Recruitment 2014", "ONGC GT 2014 Finally!!", "NU-ICICI MBA (ICICI Bank Business Leadership Programme)", "An alternative career path post MBA - Analytics - Top 5 courses", "IBPS PO4...... Exam Preparation....." };
        final String[] LOUNGE_DISCUSSIONS = { "An interview of Dr. Dinesh Awasthi, Director at EDI about his views on entrepreneurship.", "Rise To Smile ..... -:)", "Best one liners - Season 2", "LIFE @ IILM GSM", "ICICI Bank Probationary Officers Recruitment - November 2013 and February 2014 Batch" };

        for (String title: FORUMS){
            ContentValues forum = new ContentValues();
            forum.put(PGContract.Forums.COLUMN_NAME_TITLE, title);
            forum.put(PGContract.Forums.COLUMN_NAME_CREATE_DATE, now);
            forum.put(PGContract.Forums.COLUMN_NAME_MODIFICATION_DATE, now);
            Long forumId = db.insert(PGContract.Forums.TABLE_NAME, null, forum);
            if(forumId > 0){
                String [] workingForum;
                if(title == FORUMS[0]){
                    workingForum = CAT_DISCUSSIONS;
                } else if (title == FORUMS[1]){
                    workingForum = XAT_DISCUSSIONS;
                } else if (title == FORUMS[2]){
                    workingForum = BANKPO_DISCUSSIONS;
                } else if (title == FORUMS[3]){
                    workingForum = GMAT_DISCUSSIONS;
                } else if (title == FORUMS[4]){
                    workingForum = GRE_DISCUSSIONS;
                } else if (title == FORUMS[5]){
                    workingForum = JOBS_DISCUSSIONS;
                } else {
                    workingForum = LOUNGE_DISCUSSIONS;
                }
                for (String dTitle: workingForum){
                    ContentValues discussion = new ContentValues();
                    discussion.put(PGContract.Discussions.COLUMN_NAME_TITLE, dTitle);
                    discussion.put(PGContract.Discussions.COLUMN_NAME_CREATE_DATE, now);
                    discussion.put(PGContract.Discussions.COLUMN_NAME_MODIFICATION_DATE, now);
                    discussion.put(PGContract.Discussions.COLUMN_NAME_FORUM_ID, forumId);
                    Long discussionId = db.insert(PGContract.Discussions.TABLE_NAME, null, discussion);
                }
            }
        }
    }
}
