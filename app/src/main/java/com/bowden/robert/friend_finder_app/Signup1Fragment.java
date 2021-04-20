package com.bowden.robert.friend_finder_app;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.DatePickerDialog;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;

import com.bowden.robert.friend_finder_app.CustomAdapters.EditInterestsAdapter;
import com.bowden.robert.friend_finder_app.ServerClasses.Profile;
import com.bowden.robert.friend_finder_app.ServerClasses.UserProfile;
import com.bowden.robert.friend_finder_app.ServerClasses.interests;

import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.bowden.robert.friend_finder_app.ServerClasses.UserProfile.userProfile;

public class Signup1Fragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    // Members
    View view;
    String nameFirst;
    String nameLast;
    String username;
    String emailAddress;
    String password;
    String dob;
    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private boolean mIsBackVisible;
    private View mCardFrontLayout;
    private View mCardBackLayout;
    private List<String> listOfInterests = interests.getInterests();
    int i = 0;
    // Props
    GridView userInterests;
    EditText nameFirstEditText;
    EditText nameLastEditText;
    EditText usernameEditText;
    EditText emailEditText;
    EditText passwordEditText;
    Button showDate;
    Button saveDetails;

    private static String USER_TAG = "UserProfile";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_signup1, container, false);

        setProps();
        findViews();
        loadAnimations();
        changeCameraDistance();
        setShowDate();
        setButtonSaveDetails();
        setGridViewClickable();

        return view;
    }

    /*
    initialise the props of layout_signup1
    then create a method onClick of button save to set the user's details to the UserProfile class.
     */
    private void setProps () {
        nameFirstEditText = view.findViewById(R.id.editTextNameFirst);
        nameLastEditText = view.findViewById(R.id.editTextNameLast);
        usernameEditText = view.findViewById(R.id.editTextUsername);
        emailEditText = view.findViewById(R.id.editTextEmailAddress);
        passwordEditText = view.findViewById(R.id.editText8);
        showDate = view.findViewById(R.id.buttonCalendarDialog);
        saveDetails = view.findViewById(R.id.buttonSaveDetails);
        userInterests = view.findViewById(R.id.userHobbies);
        EditInterestsAdapter adapter = new EditInterestsAdapter(listOfInterests, getContext());
        userInterests.setAdapter(adapter);
    }

    private void setGridViewClickable() {
        userInterests.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                userProfile.addInterest(listOfInterests.get(position));
                view.setBackground(getActivity().getDrawable(R.drawable.round_button_selected));
                Log.i("Interest", "---> " + userProfile.getInterests().get(i));
                i++;
            }
        });
    }

    private void setShowDate() {
        showDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }
    private void showDatePickerDialog() {
        DatePickerDialog dialog = new DatePickerDialog(
                view.getContext(),
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        dialog.show();
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        dob = year + "-" + month + "-" + dayOfMonth;
    }

    private void setButtonSaveDetails() {
        saveDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nameFirst = nameFirstEditText.getText().toString();
                nameLast = nameLastEditText.getText().toString();
                username = usernameEditText.getText().toString();
                emailAddress = emailEditText.getText().toString();
                password = passwordEditText.getText().toString();

                userProfile.setNamefirst(nameFirst);
                userProfile.setNamelast(nameLast);
                userProfile.setUsername(username);
                userProfile.setEmail(emailAddress);
                userProfile.setDob(dob);
                userProfile.setPassword(password);

                Log.i(USER_TAG, "Name First: " + userProfile.getNamefirst());
                Log.i(USER_TAG, "Name Last: " + userProfile.getNamelast());
                Log.i(USER_TAG, "Username: " + userProfile.getUsername());
                Log.i(USER_TAG, "Email: " + userProfile.getEmail());
                Log.i(USER_TAG, "DOB: " + userProfile.getDob());
                Log.i(USER_TAG, "Password: " + userProfile.getPassword());

                // Add a call to card flip here.
                flipCard(mCardFrontLayout);
            }
        });
    }

    private void changeCameraDistance() {
        int distance = 8000;
        float scale = getResources().getDisplayMetrics().density * distance;
        mCardFrontLayout.setCameraDistance(scale);
        mCardBackLayout.setCameraDistance(scale);
    }

    private void loadAnimations() {
        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.out_animation);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.in_animation);
    }

    private void findViews() {
        mCardBackLayout = view.findViewById(R.id.cardSignup1Back);
        mCardFrontLayout = view.findViewById(R.id.cardSignup1Front);
        mCardBackLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard(v);
            }
        });
        mCardFrontLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard(v);
            }
        });
    }

    public void flipCard(View view) {
        if (!mIsBackVisible) {
            mSetRightOut.setTarget(mCardFrontLayout);
            mSetLeftIn.setTarget(mCardBackLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = true;
            mCardBackLayout.bringToFront();
        } else {
            mSetRightOut.setTarget(mCardBackLayout);
            mSetLeftIn.setTarget(mCardFrontLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = false;
            mCardFrontLayout.bringToFront();
        }
    }
}
